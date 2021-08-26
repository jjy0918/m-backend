package com.midas.epkorea.controller;

import com.midas.epkorea.domain.managerlog.ManagerLogRepository;
import com.midas.epkorea.service.ManagerLogService;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managerlog")
@RequiredArgsConstructor
public class ManagerLogController {

    private final ManagerLogService managerLogService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllManagerLog(@RequestParam(defaultValue = "1") int page ){
        page--;
        return managerLogService.getAllManagerLog(page);
    }

    @GetMapping("/search/id")
    public ResponseEntity<ResponseDto> searchManagers(@RequestParam(defaultValue = "1") int page, @RequestParam String word){
        page--;
        return managerLogService.searchManagerLog(page,word);
    }

}
