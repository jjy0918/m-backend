package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.ManagerRepository;
import com.midas.epkorea.exception.PageException;
import com.midas.epkorea.util.ManagerResponseDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ResponseEntity<ResponseDto> getAllManagers(int page){
        PageRequest pageRequest=null;
        try{
            // 페이지 번호, 시작, 정렬
            pageRequest = PageRequest.of(page, 10, Sort.by("no").descending());

        }catch (IllegalArgumentException illException){
            // 요청하려는 페이지가 0이하인 작은 경우 예외 발생시킨다.
            throw new PageException();
        }

        // 따로 개수 확인하는 것이 좋은가???
        ManagerResponseDto managerResponseDto = new ManagerResponseDto(managerRepository.findAll(pageRequest));
        ResponseDto responseDto = ResponseDto.builder()
                .message("find all managers")
                .data(managerResponseDto)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }


}
