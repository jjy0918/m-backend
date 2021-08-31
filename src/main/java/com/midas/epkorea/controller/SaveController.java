package com.midas.epkorea.controller;

import com.midas.epkorea.exception.FileNameNotFoundException;
import com.midas.epkorea.service.SaveService;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/save")
public class SaveController {

    private final SaveService saveService;


    @PostMapping
    public ResponseEntity<ResponseDto> saveImage(@RequestParam MultipartFile file) throws IOException, FileNameNotFoundException {

        return saveService.registerImageIntoServer(file);

    }


}
