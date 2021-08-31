package com.midas.epkorea.controller;

import com.midas.epkorea.dto.ConstructionEditRequestDto;
import com.midas.epkorea.dto.ConstructionRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.ProductManagementNotPresentException;
import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/construction")
@RequiredArgsConstructor
public class ConstructionController {

    private final ConstructionService constructionService;


    @GetMapping
    public ResponseEntity<ResponseDto> getAllConstruction(@RequestParam(defaultValue = "1") int page ){
        page--;

        return constructionService.getAllConstruction(page);
    }

    @GetMapping("/search/name")
    public ResponseEntity<ResponseDto> searchConstructionByName(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        page--;
        return constructionService.searchConstructionByName(page,word);
    }

    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> searchConstructionDetailByNo(@PathVariable int no) throws ProductManagementNotPresentException {
        return constructionService.searchConstructionDetailByNo(no);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createConstruction(@RequestBody ConstructionRequestDto requestDto) throws RequiredValueException {
        requestDto.check();
        return constructionService.createConstruction(requestDto);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<ResponseDto> deleteConstruction(@PathVariable int no) throws ProductManagementNotPresentException {
        return constructionService.deleteConstruction(no);
    }

    @PutMapping("/{no}")
    public ResponseEntity<ResponseDto> editConstruction(@PathVariable int no, @RequestBody ConstructionEditRequestDto requestDto) throws ProductManagementNotPresentException {
        return constructionService.editConstruction(no, requestDto);
    }




}
