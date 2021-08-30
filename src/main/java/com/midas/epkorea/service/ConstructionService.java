package com.midas.epkorea.service;

import com.midas.epkorea.domain.construction.ConstructionRepository;
import com.midas.epkorea.dto.ConstructionResponseDto;
import com.midas.epkorea.dto.ManagerLogResponseDto;
import com.midas.epkorea.dto.PageDto;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    public ResponseEntity<ResponseDto> getAllConstruction(int page) {
        Pageable pageRequest = PageDto.getPageRequest(page);
        ConstructionResponseDto contructionResponseDto = new ConstructionResponseDto(constructionRepository.findAll(pageRequest));

       ResponseDto responseDto = ResponseDto.builder()
                .message("find all managersLog")
                .data(contructionResponseDto)
                .build();

       return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}
