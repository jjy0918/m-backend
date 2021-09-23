package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.dto.*;
import com.midas.epkorea.dto.request.ManagerEditRequestDto;
import com.midas.epkorea.dto.request.ManagerRequestDto;
import com.midas.epkorea.dto.response.ManagerResponseDto;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.exception.UserPresentException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService implements UserDetailsService {

    private final ManagerRepository managerRepository;


    public ResponseEntity<ManagerResponseDto> getAllManagers(int page){

        Pageable pageRequest = PageDto.getPageRequest(page);

        // 따로 개수 확인하는 것이 좋은가???
        ManagerResponseDto managerResponseDto = new ManagerResponseDto(managerRepository.findAll(pageRequest));
        managerResponseDto.setMessage("find all managers");

        return new ResponseEntity<>(managerResponseDto,HttpStatus.OK);
    }


    public ResponseEntity<ManagerResponseDto> searchManagers(int page, String type, String word) {
        Pageable pageRequest = PageDto.getPageRequest(page);

        ManagerResponseDto managerResponseDto = null;

        if(type.equals("name")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByNameContains(word,pageRequest));
            managerResponseDto.setMessage("find managers by name");
        }
        else if(type.equals("belong")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByBelongContains(word,pageRequest));
            managerResponseDto.setMessage("find managers by belong");
        }
        else if(type.equals("all")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByIdContainsOrBelongContains(word,word,pageRequest));
            managerResponseDto.setMessage("find managers by all");
        }


        return new ResponseEntity<>(managerResponseDto, HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> createManager(ManagerRequestDto manager) throws UserPresentException {

        Optional<Manager> managerOptional = managerRepository.findById(manager.getId());

        if(managerOptional.isPresent()){
            throw new UserPresentException();
        }

        Manager saveManager = Manager.builder().build();

        saveManager.createManagerByManagerRequest(manager);
        managerRepository.save(saveManager);

        ResponseDto responseDto = ResponseDto.builder()
                .message("manager create")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED );


    }

    @Transactional
    public ResponseEntity<ResponseDto> editManager(ManagerEditRequestDto managerRequestDto, int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        getManager.createManagerByManagerRequest(managerRequestDto);

        ResponseDto responseDto = ResponseDto.builder()
                .message("manager update")
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED );

    }

    public ResponseEntity<ResponseDto> getManagerByNo(int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("find manager by no")
                .data(getManager)
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> deleteManger(int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        managerRepository.delete(getManager);

        ResponseDto responseDto = ResponseDto.builder()
                .message("delete manager")
                .build();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);


    }

    // 번호로 관리자 받아오기
    private Manager getManager(int no) throws UserNotPresentException {
        Optional<Manager> managerOptional = managerRepository.findById(no);

        return managerOptional.orElseThrow(UserNotPresentException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Optional<Manager> managerOptional = managerRepository.findById(id);
        return  managerOptional.orElseThrow(()->new UsernameNotFoundException(id));
    }
}
