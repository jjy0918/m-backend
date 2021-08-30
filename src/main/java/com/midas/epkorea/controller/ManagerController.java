package com.midas.epkorea.controller;

import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.exception.UserPresentException;
import com.midas.epkorea.service.ManagerService;
import com.midas.epkorea.dto.ManagerRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    // 관리자 전체 목록 받아오기
    @GetMapping()
    public ResponseEntity<ResponseDto> getAllMangers(@RequestParam(defaultValue = "1") int page){
        page--;
        return managerService.getAllManagers(page);
    }

    // 관리자 정보 번호로 받아오기
    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> getManagerByNo(@PathVariable int no) throws UserNotPresentException {
        return managerService.getManagerByNo(no);
    }

    // 관리자 검색
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

    // 관리자 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<ResponseDto> deleteManger(@PathVariable int no) throws UserNotPresentException {
        return managerService.deleteManger(no);
    }



}
