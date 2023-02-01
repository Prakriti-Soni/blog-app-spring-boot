package com.blogging.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;



import com.blogging.backend.security.CustomUserDetailsService;

import com.blogging.backend.security.JwtAuthenticationEntryPoint;

import com.blogging.backend.security.JwtAuthenticationFilter;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

import io.swagger.v3.oas.annotations.info.Contact;

import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.security.SecurityScheme;



@Configuration
@EnableWebSecurity
@EnableWebMvc
@OpenAPIDefinition(info = @Info(title = "Blogging Application APIs",

        description = "This project is developed by Prakriti Soni", version = "v1", termsOfService = "Terms of service",

        contact = @Contact(name = "Prakriti", email = "prakriti17soni@gmail.com", url = "https://google.com")

))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {



    private static final String[] AUTH_WHITELIST = {

            "/api/v1/auth/**",

            "/swagger-resources/**",

            "/swagger-ui/**",

            "/v3/api-docs/**",

            "/v2/api-docs",

            "/webjars/**"

    };

//http://localhost:8080/swagger-ui/index.html

//http://localhost:8080/v3/api-docs



    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Autowired
    private CustomUserDetailsService customUserDetailService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf-> csrf.disable())

                .authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll()

                        .requestMatchers(HttpMethod.GET).permitAll()

                        .anyRequest().authenticated())

                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        http.headers().frameOptions().disable();

        return http.build();

    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)

            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()

    {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(this.customUserDetailService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}




