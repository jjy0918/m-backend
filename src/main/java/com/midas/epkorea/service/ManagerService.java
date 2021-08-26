package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.exception.PageException;
import com.midas.epkorea.util.ManagerResponseDto;
import com.midas.epkorea.util.PageDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
