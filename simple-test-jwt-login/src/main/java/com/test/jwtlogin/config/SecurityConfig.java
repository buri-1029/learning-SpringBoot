package com.test.jwtlogin.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @EnableWebSecurity : 기본적인 Web 보안을 활성화 하겠다는 의미
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurer를 implements 하거나
    // WebSecurityConfigurerAdapter를 extends 하는 2가지 방법

    // h2-console 하위 모든 요청들과 파비콘 관련 요청은 Spring Security 로직을 수행하지 않도록
    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                );
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/hello").permitAll() // "/user/hello"에 대한 요청은 인증없이 접근을 허용하겠다는 의미
                .anyRequest().authenticated(); // 나머지 요청들은 모두 인증되어야 한다는 의미
    }
}
