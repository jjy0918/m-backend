package com.midas.epkorea.controller;

import com.midas.epkorea.dto.LoginRequestDto;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.UserNotPresentException;
import com.midas.epkorea.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) throws UserNotPresentException {
        return loginService.login(requestDto);
    }

}
