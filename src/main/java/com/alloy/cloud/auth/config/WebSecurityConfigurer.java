package com.alloy.cloud.auth.config;

import com.alloy.cloud.common.security.handler.FormAuthenticationFailureHandler;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Primary
@Order(90)
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.formLogin().loginPage("/token/login").loginProcessingUrl("/token/form")
                .failureHandler(authenticationFailureHandler()).and().authorizeRequests()
                .antMatchers("/token/**", "/actuator/**", "/mobile/**").permitAll().anyRequest().authenticated().and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new FormAuthenticationFailureHandler();
    }

    /**
     * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated
     * Encoded password does not look like BCrypt
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}