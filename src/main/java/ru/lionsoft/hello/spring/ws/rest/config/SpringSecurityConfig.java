/*
 * File:    SpringSecurityConfig.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security configuration setup
 * 
 * @author Igor Morenko
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Настройка базовой аутентификации и авторизация сервисов API
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic() // Use Basic Authentication 
            .and()
                .authorizeRequests()
                .antMatchers("/api/**") //.anyRequest()
                .hasAnyRole("ADMIN", "USER"); 
    }

    // Настройка встроенного провайдера аутентификации
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Built-in authentication provider
        auth
            .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin@password") // {noop} makes sure that the password encoder doesn't do anything
                .roles("ADMIN") // Role of the user
            .and()
                .withUser("user")
                .password("{noop}user@password")
                .credentialsExpired(true)
                .accountExpired(true)
                .accountLocked(true)
                .roles("USER")
            .and()
                .withUser("scott")
                .password("{noop}tiger")
                .roles("USER");                
    }

}
