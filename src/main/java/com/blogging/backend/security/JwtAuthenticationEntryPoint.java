package com.blogging.backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {



            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Un-Authorized !!");

        } else {

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denined !!");

        }

      //  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!");
    }
}
