package com.midas.epkorea.service;

import com.midas.epkorea.domain.construction.ConstructionRepository;
import com.midas.epkorea.dto.ConstructionResponseDto;
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

    public ResponseEntity<ResponseDto> searchConstructionByName(int page, String word) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        ConstructionResponseDto constructionResponseDto = new ConstructionResponseDto(constructionRepository.findAllByNameContains(pageRequest,word));
        ResponseDto responseDto = ResponseDto.builder()
                .message("search construction by name")
                .data(constructionResponseDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);


    }
}
