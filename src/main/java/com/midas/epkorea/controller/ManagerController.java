package com.midas.epkorea.controller;

import com.midas.epkorea.domain.manager.Manager;
import com.midas.epkorea.dto.ManagerEditRequestDto;
import com.midas.epkorea.dto.ManagerRequestDto;
import com.midas.epkorea.dto.ManagerResponseDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.RequiredValueException;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.exception.UserPresentException;
import com.midas.epkorea.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    // 관리자 전체 목록 받아오기
    @GetMapping()
    public ResponseEntity<ManagerResponseDto> getAllMangers(@RequestParam(defaultValue = "1") int page){
        return managerService.getAllManagers(page);
    }

    // 관리자 정보 번호로 받아오기
    @GetMapping("/{no}")
    public ResponseEntity<ResponseDto> getManagerByNo(@PathVariable int no) throws UserNotPresentException {
        return managerService.getManagerByNo(no);
    }

    // 관리자 검색
    @GetMapping("/search/{type}")
    public ResponseEntity<ManagerResponseDto> searchManagers(@RequestParam(defaultValue = "1") int page, @PathVariable String type, @RequestParam String word){
        return managerService.searchManagers(page,type,word);
    }

    // 관리자 생성
    @PostMapping
    public ResponseEntity<ResponseDto> createManager(@RequestBody @Valid ManagerRequestDto managerRequestDto) throws UserPresentException, RequiredValueException {
        managerRequestDto.checkRequiredValue();
        return managerService.createManager(managerRequestDto);
    }

    // 관리자 수정
    @PutMapping("/{no}")
    public ResponseEntity<ResponseDto> editManager(@RequestBody @Valid ManagerEditRequestDto managerRequestDto, @PathVariable int no, HttpServletRequest request) throws RequiredValueException, UserNotPresentException {
        managerRequestDto.checkRequiredValue();
        
        ResponseEntity<ResponseDto> result = managerService.editManager(managerRequestDto,no);
        
        // 에러 발생 x => 정상 수정
        // 현재 로그인 된 값이 변경되었다 => 재로그인 필요
        if(Manager.getManager().getNo() == no){
            HttpSession httpSession = request.getSession();
            httpSession.invalidate();
        }
        return result;
    }

    // 관리자 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<ResponseDto> deleteManger(@PathVariable int no, HttpServletRequest request) throws UserNotPresentException {
        ResponseEntity<ResponseDto> result = managerService.deleteManger(no);

        // 에러 발생 x => 정상 수정
        // 현재 로그인 된 값이 변경되었다 => 재로그인 필요
        if(Manager.getManager().getNo() == no){
            HttpSession httpSession = request.getSession();
            httpSession.invalidate();
        }
        return result;
    }





}
