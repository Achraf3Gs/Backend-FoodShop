package com.guess.foodback.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private  final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf()
                .disable()

                .authorizeHttpRequests()
                .requestMatchers("/", "/index.html", "/home", "/styles**", "/runtime**", "/polyfills**",
                        "/main**","/favicon.png", "/assets/foods/**","/assets/stars/**").permitAll()
                .requestMatchers("/api/v1/auth/register","/api/v1/auth/authenticate" )
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
                .requestMatchers("/api/v1/auth/foods","/api/v1/auth/foods/tags" )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }




}

