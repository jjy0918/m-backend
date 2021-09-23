package com.midas.epkorea.controller;

import com.midas.epkorea.dto.ConstructionRequestDto;
import com.midas.epkorea.dto.ConstructionResponseDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.midas.epkorea.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/construction")
@RequiredArgsConstructor
public class ConstructionController {

    private final ConstructionService constructionService;


    @GetMapping
    public ResponseEntity<ConstructionResponseDto> getAllConstruction(@RequestParam(defaultValue = "1") int page ){
          return constructionService.getAllConstruction(page);
    }

    @GetMapping("/search/name")
    public ResponseEntity<ConstructionResponseDto> searchConstructionByName(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        return constructionService.searchConstructionByName(page,word);
    }

    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> searchConstructionDetailByNo(@PathVariable int no) throws ProductManagementNotPresentException {
        return constructionService.searchConstructionDetailByNo(no);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createConstruction(@RequestBody @Valid ConstructionRequestDto requestDto) {
        return constructionService.createConstruction(requestDto);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<ResponseDto> deleteConstruction(@PathVariable int no) throws ProductManagementNotPresentException {
        return constructionService.deleteConstruction(no);
    }

    @PutMapping("/{no}")
    public ResponseEntity<ResponseDto> editConstruction(@PathVariable int no, @RequestBody @Valid ConstructionRequestDto requestDto) throws ProductManagementNotPresentException {
        return constructionService.editConstruction(no, requestDto);
    }




}
