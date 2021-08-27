package com.midas.epkorea.service;

import com.midas.epkorea.domain.productmanagetment.ProductManagementRepository;
import com.midas.epkorea.util.ManagerResponseDto;
import com.midas.epkorea.util.PageDto;
import com.midas.epkorea.util.ProductManagementResponseDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductManagetmentService {

    final private ProductManagementRepository productManagementRepository;

    public ResponseEntity<ResponseDto> getProductManagementList(int page) {

        Pageable pageRequest = PageDto.getPageRequest(page);

        // 따로 개수 확인하는 것이 좋은가???
        ProductManagementResponseDto productManagementResponseDto = new ProductManagementResponseDto(productManagementRepository.findAll(pageRequest));
        ResponseDto responseDto = ResponseDto.builder()
                .message("find all managers")
                .data(productManagementResponseDto)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
