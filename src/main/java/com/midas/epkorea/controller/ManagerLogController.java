package com.midas.epkorea.controller;

import com.midas.epkorea.dto.response.ManagerLogResponseDto;
import com.midas.epkorea.service.ManagerLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/managerlog")
@RequiredArgsConstructor
public class ManagerLogController {

    private final ManagerLogService managerLogService;

    @GetMapping
    public ResponseEntity<ManagerLogResponseDto> getAllManagerLog(@RequestParam(defaultValue = "1") int page ){
        return managerLogService.getAllManagerLog(page);
    }

    @GetMapping("/search/id")
    public ResponseEntity<ManagerLogResponseDto> searchManagers(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        return managerLogService.searchManagerLog(page,word);
    }

}
