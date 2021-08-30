package com.midas.epkorea.controller;

import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.service.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/construction")
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


}
