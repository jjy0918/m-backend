package com.midas.epkorea.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 관리자 생성, 수정 등은 ADMIN 권한을 가진 사용자만 가능
                .antMatchers("/api/manager/**","/api/managerlog/**").hasRole("ADMIN")
                // 제품, 구축 사례 등록, 수정 등은 ADMIN, MANAGER 권한을 가진 사용자만 가능
                .antMatchers("/api/construction/**","/api/productmanagement/**","/api/save/**").hasAnyRole("ADMIN","MANAGER")
                // 인증이 필요 없는 부분 : 로그인
                .antMatchers("/api/login").permitAll()
                // 인증이 필요 없는 부분 : 뷰 사용 부분
                .antMatchers("/css/**","/js/**","/").permitAll()
                .anyRequest().authenticated();

        http.httpBasic().and().formLogin().loginPage("/");
    }


}
