package com.midas.epkorea.controller;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.exception.UserPresentException;
import com.midas.epkorea.service.ManagerService;
import com.midas.epkorea.util.ManagerRequestDto;
import com.midas.epkorea.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search/{type}")
    public ResponseEntity<ResponseDto> searchManagers(@RequestParam(defaultValue = "1") int page, @PathVariable String type, @RequestParam String word){
        page--;
        return managerService.searchManagers(page,type,word);
    }

    // 관리자 생성
    @PostMapping
    public ResponseEntity<ResponseDto> createManager(@RequestBody ManagerRequestDto manager) throws UserPresentException, RequiredValueException {
        manager.checkRequiredValue();
        return managerService.createManager(manager);
    }

    // 관리자 수정
    @PutMapping("/{no}")
    public ResponseEntity<ResponseDto> editManager(@RequestBody ManagerRequestDto managerRequestDto, @PathVariable int no) throws RequiredValueException, UserNotPresentException {
        managerRequestDto.checkRequiredValue();

        return managerService.editManager(managerRequestDto,no);
    }



}
