package com.kansal.jwt_validation_lib.config;

import com.kansal.jwt_validation_lib.connector.ValidatingServiceConnector;
import com.kansal.jwt_validation_lib.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidatingServiceConnector validatingServiceConnector;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .addFilterAfter(new JwtValidationFilter(validatingServiceConnector), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();
    }
}
