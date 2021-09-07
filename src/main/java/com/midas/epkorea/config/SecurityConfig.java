package com.midas.epkorea.config;

import com.midas.epkorea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN="ADMIN";
    private static final String MANAGER="MANAGER";
    private static final String USER="USER";

    private final UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 관리자 생성, 수정 등은 ADMIN 권한을 가진 사용자만 가능
//                .antMatchers("/api/manager/**","/api/managerlog/**").hasRole(ADMIN)
                // 제품, 구축 사례 등록, 수정 등은 ADMIN, MANAGER 권한을 가진 사용자만 가능
//                .antMatchers("/api/construction/**","/api/save/**","/api/productmanagement/**").hasAnyRole(ADMIN,MANAGER)
                // 인증이 필요 없는 부분 : 로그인, 회원가입
                .antMatchers("/api/login","/api/user/**","/login","/user").permitAll()
                // 인증이 필요 없는 부분 : 뷰 사용 부분
                .antMatchers("/css/**","/js/**","/").permitAll()
                // 임시 수정
                .anyRequest().permitAll()
                .and().csrf().disable();

        http.httpBasic().and().formLogin().loginPage("/login").defaultSuccessUrl("/manager")
                .and().logout().logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(userService);
                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
                // loadUserByUsername() 구현해야함 (서비스 참고)
//                .passwordEncoder(new BCryptPasswordEncoder());
    }


}
