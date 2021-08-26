package com.midas.epkorea.controller;

import com.midas.epkorea.service.ManagerService;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping()
    public ResponseEntity<ResponseDto> getAllMangers(@RequestParam(defaultValue = "1") int page){
        page--;
        return managerService.getAllManagers(page);
    }



}
