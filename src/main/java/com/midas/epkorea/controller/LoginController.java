package com.midas.epkorea.controller;

import com.midas.epkorea.domain.manager.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping
    public String getUserInfo(@AuthenticationPrincipal Manager manager){
        return manager.getId();
    }

    @GetMapping("/check/admin")
    public boolean checkAdmin(){
        return true;
    }

    @GetMapping("/check/manager")
    public boolean checkManager(){
        return true;
    }

}
