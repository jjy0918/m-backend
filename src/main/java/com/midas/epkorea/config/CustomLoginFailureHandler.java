package com.midas.epkorea.config;

import com.midas.epkorea.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 로그인 실패시 처리
@RequiredArgsConstructor
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final LoginService loginService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String username= request.getParameter("username");

        loginService.setManagerLogFailure(username,request.getRemoteAddr(),request.getSession().getId());
    }
}
