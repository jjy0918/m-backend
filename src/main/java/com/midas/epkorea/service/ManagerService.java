package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.exception.UserPresentException;
import com.midas.epkorea.dto.ManagerRequestDto;
import com.midas.epkorea.dto.ManagerResponseDto;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;



    public ResponseEntity<ResponseDto> getAllManagers(int page){

        Pageable pageRequest = PageDto.getPageRequest(page);

        // 따로 개수 확인하는 것이 좋은가???
        ManagerResponseDto managerResponseDto = new ManagerResponseDto(managerRepository.findAll(pageRequest));
        ResponseDto responseDto = ResponseDto.builder()
                .message("find all managers")
                .data(managerResponseDto)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }


    public ResponseEntity<ResponseDto> searchManagers(int page, String type, String word) {
        Pageable pageRequest = PageDto.getPageRequest(page);

        String message = "타입 설정이 잘못되었습니다.";
        ManagerResponseDto managerResponseDto = null;

        if(type.equals("name")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByNameContains(word,pageRequest));
            message = "find managers by name";
        }
        else if(type.equals("belong")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByBelongContains(word,pageRequest));
            message = "find managers by belong";
        }
        else if(type.equals("all")){
            managerResponseDto = new ManagerResponseDto(managerRepository.findAllByIdContainsOrBelongContains(word,word,pageRequest));
            message = "find managers by all";
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message(message)
                .data(managerResponseDto)
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);

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
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED );


    }

    @Transactional
    public ResponseEntity<ResponseDto> editManager(ManagerRequestDto managerRequestDto, int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        getManager.createManagerByManagerRequest(managerRequestDto);

        ResponseDto responseDto = ResponseDto.builder()
                .message("manager update")
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED );

    }

    public ResponseEntity<ResponseDto> getManagerByNo(int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("find manager by no")
                .data(getManager)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);

    }

    public ResponseEntity<ResponseDto> deleteManger(int no) throws UserNotPresentException {

        Manager getManager = getManager(no);

        managerRepository.delete(getManager);

        ResponseDto responseDto = ResponseDto.builder()
                .message("delete manager")
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);


    }

    // 번호로 관리자 받아오기
    private Manager getManager(int no) throws UserNotPresentException {
        Optional<Manager> managerOptional = managerRepository.findById(no);

        Manager getManager = managerOptional.orElseThrow(()->new UserNotPresentException());

        return getManager;
    }
}
