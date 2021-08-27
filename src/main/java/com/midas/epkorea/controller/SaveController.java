package com.midas.epkorea.controller;

import com.midas.epkorea.service.SaveService;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/save")
public class SaveController {

    private final SaveService saveService;


    @PostMapping
    public ResponseEntity<ResponseDto> saveImage(@RequestParam MultipartFile file) throws IOException {

        return saveService.registerImageIntoServer(file);

    }


}
