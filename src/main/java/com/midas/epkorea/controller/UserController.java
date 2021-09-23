package com.midas.epkorea.controller;

import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.dto.request.UserRequestDto;
import com.midas.epkorea.exception.UserPresentException;
import com.midas.epkorea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid UserRequestDto requestDto) throws UserPresentException {
        return userService.createUser(requestDto);
    }



}
