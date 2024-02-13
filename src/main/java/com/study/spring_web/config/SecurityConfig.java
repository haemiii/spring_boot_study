package com.study.spring_web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity  // 시큐리티 설정을 직접 하겠다
@EnableWebMvc
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 개발시에 필요한 몇몇 Url만 허용
        // profile은 GET요청만 허용
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/sign-up", "/check-email", "/check-email-token",
                                "/email-login", "/check-email-login", "/login-link").permitAll()
                        .requestMatchers(HttpMethod.GET, "/profile/*").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();

    }
    @Bean
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests((auth) -> auth
                 .requestMatchers("/node_modules/**").permitAll()
                 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll());

         return http.build();
    }
}