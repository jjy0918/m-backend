package com.midas.epkorea.service;

import com.midas.epkorea.domain.managerlog.ManagerLogRepository;
import com.midas.epkorea.util.ManagerLogResponseDto;
import com.midas.epkorea.util.ManagerResponseDto;
import com.midas.epkorea.util.PageDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerLogService {

    private final ManagerLogRepository managerLogRepository;


    public ResponseEntity<ResponseDto> getAllManagerLog(int page) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        // 따로 개수 확인하는 것이 좋은가???
        ManagerLogResponseDto managerLogResponseDto = new ManagerLogResponseDto(managerLogRepository.findAll(pageRequest));
        ResponseDto responseDto = ResponseDto.builder()
                .message("find all managersLog")
                .data(managerLogResponseDto)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> searchManagerLog(int page, String word) {
        Pageable pageRequest = PageDto.getPageRequest(page);
        ManagerLogResponseDto managerLogResponseDto = new ManagerLogResponseDto(managerLogRepository.findAllByIdContains(word,pageRequest));
        ResponseDto responseDto = ResponseDto.builder()
                .message("find managerLog by id")
                .data(managerLogResponseDto)
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
