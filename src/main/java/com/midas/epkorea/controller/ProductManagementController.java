package com.midas.epkorea.controller;

import com.midas.epkorea.dto.ProductManagementResponseDto;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.service.ProductManagetmentService;
import com.midas.epkorea.dto.ProductManagementRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productmanagement")
public class ProductManagementController {

    private final ProductManagetmentService productManagetmentService;

    // 전체 리스트 받아오기
    @GetMapping
    public ResponseEntity<ProductManagementResponseDto> getProductManagementList(@RequestParam(defaultValue = "1") int page ){
        return productManagetmentService.getProductManagementList(page);

    }

    // 특정 리스트 받아오기
    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> getProductManagementByNo(@PathVariable int no) throws ProductManagementNotPresentException {
        return productManagetmentService.getProductManagementByNo(no);

    }

    @GetMapping("/search/name")
    public ResponseEntity<ProductManagementResponseDto> searchProductManagement(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        return productManagetmentService.searchProductManagement(page,word);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createProductManagement(@RequestBody @Valid ProductManagementRequestDto requestDto) {
        return productManagetmentService.createProductManagement(requestDto);
    }




}
