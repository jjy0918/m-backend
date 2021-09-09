package com.midas.epkorea.config;

import com.midas.epkorea.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN="ADMIN";
    private static final String MANAGER="MANAGER";
    private static final String USER="USER";

    private final ManagerService managerService;

    private final CustomLoginFailureHandler customLoginFailureHandler;
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    // 필터 체인으로 등록해서, 정적 리소스는 항상 통과 가능하도록 설정한다.
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // 관리자 생성, 수정 등은 ADMIN 권한을 가진 사용자만 가능
                .antMatchers("/api/manager/**","/api/managerlog/**","/api/user/**","/api/login/check/admin").hasAnyRole(ADMIN)
                // 제품, 구축 사례 등록, 수정 등은 ADMIN, MANAGER 권한을 가진 사용자만 가능
                .antMatchers("/api/construction/**","/api/save/**","/api/productmanagement/**","/api/login","/api/login/check/manager").hasAnyRole(ADMIN,MANAGER)
                // 인증이 필요 없는 부분 : 로그인, 회원가입
                .antMatchers("/user").permitAll().and()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);



        http.httpBasic().and().formLogin().loginPage("/login").failureHandler(customLoginFailureHandler).successHandler(customLoginSuccessHandler)
                .and().logout().logoutSuccessHandler(customLogoutSuccessHandler) // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
       // AuthenticationManager에 등록
       // 여기서 유저 정보 가져온다.
        auth.userDetailsService(managerService)
                .passwordEncoder(passwordEncoder());


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
