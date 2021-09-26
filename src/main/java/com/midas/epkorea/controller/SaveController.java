package com.midas.epkorea.controller;

import com.midas.epkorea.exception.FileExtentionException;
import com.midas.epkorea.exception.FileNameNotFoundException;
import com.midas.epkorea.service.SaveService;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/save")
public class SaveController {

    private final SaveService saveService;

    private final String[] imageExtentions=new String[]{
            "tiff","pjp","jfif","bmp","gif","svg","png","xbm","dib","jxl","jpeg","svgz","jpg","webp","ico","tif","pjpeg","avif"
    };


    @PostMapping("/image")
    public ResponseEntity<ResponseDto> saveImage(@RequestParam MultipartFile file) throws IOException, FileNameNotFoundException, FileExtentionException {
        String extension = saveService.getExtention(file);
        if(!Arrays.stream(imageExtentions).anyMatch(image -> image.equals(extension))){
            throw new FileExtentionException("이미지 파일만 등록할 수 있습니다.");
        }

        return saveService.registerImageIntoServer(file);

    }

    @PostMapping("/pdf")
    public ResponseEntity<ResponseDto> savePdf(@RequestParam MultipartFile file) throws IOException, FileNameNotFoundException, FileExtentionException {
        String extension = saveService.getExtention(file);
        if(!extension.equals("pdf")){
            throw new FileExtentionException("PDF 파일만 등록할 수 있습니다.");
        }
        return saveService.registerImageIntoServer(file);

    }


}
