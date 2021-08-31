package com.midas.epkorea.controller;

import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.service.ProductManagetmentService;
import com.midas.epkorea.dto.ProductManagementRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productmanagement")
public class ProductManagementController {

    private final ProductManagetmentService productManagetmentService;

    // 전체 리스트 받아오기
    @GetMapping
    public ResponseEntity<ResponseDto> getProductManagementList(@RequestParam(defaultValue = "1") int page ){
        page--;

        return productManagetmentService.getProductManagementList(page);

    }

    // 특정 리스트 받아오기
    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> getProductManagementByNo(@PathVariable int no) throws ProductManagementNotPresentException {
        return productManagetmentService.getProductManagementByNo(no);

    }

    @GetMapping("/search/name")
    public ResponseEntity<ResponseDto> searchProductManagement(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        page--;
        return productManagetmentService.searchProductManagement(page,word);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createProductManagement(@RequestBody ProductManagementRequestDto requestDto) throws RequiredValueException {
        requestDto.check();
        return productManagetmentService.createProductManagement(requestDto);
    }




}
