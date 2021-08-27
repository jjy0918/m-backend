package com.midas.epkorea.controller;

import com.midas.epkorea.service.ProductManagetmentService;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productmanagement")
public class ProductManagementController {

    private final ProductManagetmentService productManagetmentService;

    @GetMapping
    public ResponseEntity<ResponseDto> getProductManagementList(@RequestParam(defaultValue = "1") int page ){
        page--;

        return productManagetmentService.getProductManagementList(page);

    }




}
