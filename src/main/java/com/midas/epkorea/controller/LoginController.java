package com.midas.epkorea.controller;

import com.midas.epkorea.dto.LoginRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletRequest httpServletRequest, HttpSession httpSession) throws UserNotPresentException {
        return loginService.login(requestDto,httpServletRequest.getRemoteAddr(),httpSession.getId());
    }

}
