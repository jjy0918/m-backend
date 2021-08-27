package com.midas.epkorea.service;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.domain.productmanagetment.ProductManagement;
import com.midas.epkorea.domain.productmanagetment.ProductManagementRepository;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.util.ManagerResponseDto;
import com.midas.epkorea.util.PageDto;
import com.midas.epkorea.util.ProductManagementResponseDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManagetmentService {

    final private ProductManagementRepository productManagementRepository;

    // 번호로 관리자 받아오기
    private ProductManagement getProductManagement(int no) throws ProductManagementNotPresentException {
        Optional<ProductManagement> productManagement = productManagementRepository.findById(no);

        ProductManagement getProductManagement = productManagement.orElseThrow(()->new ProductManagementNotPresentException());

        return getProductManagement;
    }

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

    public ResponseEntity<ResponseDto> getProductManagementByNo(int no) throws ProductManagementNotPresentException {
        ProductManagement getProductManagement = getProductManagement(no);

        ResponseDto responseDto = ResponseDto.builder()
                .message("find productManagement by no")
                .data(getProductManagement)
                .build();

        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);

    }
}
