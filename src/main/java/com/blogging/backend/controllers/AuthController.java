package com.blogging.backend.controllers;

import com.blogging.backend.exceptions.ApiException;
import com.blogging.backend.payloads.JwtAuthRequest;
import com.blogging.backend.payloads.JwtAuthResponse;
import com.blogging.backend.payloads.UserDto;
import com.blogging.backend.security.JwtTokenHelper;
import com.blogging.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{

        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        System.out.println("userdetails found : " + userDetails.getUsername() + "password: " + userDetails.getPassword());

        String token =  this.jwtTokenHelper.generateToken(userDetails);

        System.out.println("got the token" + token);

        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }


    private void authenticate(String username, String password) throws Exception{
        System.out.println("inside controller" + username + password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);


        try{
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid details");
            throw new ApiException("Invalid username or password!!");
        }


    }

    @PostMapping("/register")
    private ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) {
        UserDto registeredNewUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredNewUser, HttpStatus.CREATED);
    }
}
