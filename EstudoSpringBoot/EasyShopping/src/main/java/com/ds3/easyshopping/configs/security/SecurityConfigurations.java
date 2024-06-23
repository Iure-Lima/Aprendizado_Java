package com.ds3.easyshopping.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->{
                    authorize.requestMatchers(HttpMethod.POST, "/produtos").permitAll();
                    authorize.requestMatchers(HttpMethod.PUT, "/produtos/{id}").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE, "/produtos/{id}").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE, "/vendas/{id}").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/vendas").permitAll();
                    authorize.anyRequest().permitAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
