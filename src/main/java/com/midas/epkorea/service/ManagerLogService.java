package com.midas.epkorea.service;

import com.midas.epkorea.domain.managerlog.ManagerLogRepository;
import com.midas.epkorea.dto.response.ManagerLogResponseDto;
import com.midas.epkorea.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerLogService {

    private final ManagerLogRepository managerLogRepository;


    public ResponseEntity<ManagerLogResponseDto> getAllManagerLog(int page) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        // 따로 개수 확인하는 것이 좋은가???
        ManagerLogResponseDto managerLogResponseDto = new ManagerLogResponseDto(managerLogRepository.findAll(pageRequest));
        managerLogResponseDto.setMessage("find all managersLog");

        return new ResponseEntity<>(managerLogResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ManagerLogResponseDto> searchManagerLog(int page, String word) {
        Pageable pageRequest = PageDto.getPageRequest(page);
        ManagerLogResponseDto managerLogResponseDto = new ManagerLogResponseDto(managerLogRepository.findAllByIdContains(word,pageRequest));
        managerLogResponseDto.setMessage("find managerLog by id");
        return new ResponseEntity<>(managerLogResponseDto, HttpStatus.OK);
    }
}
