package com.dyarvoy.TinkoffProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/*
 * Security disabled.
 * This class disabled default security config.
 * If will need this class can be configured for OAuth2 or HttpBasic;
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/*").permitAll();
    }
}
