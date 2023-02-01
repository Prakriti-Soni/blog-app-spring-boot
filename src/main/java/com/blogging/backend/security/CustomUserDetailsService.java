package com.blogging.backend.security;

import com.blogging.backend.entities.User;
import com.blogging.backend.exceptions.ResourceNotFoundException;
import com.blogging.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "user name : "+username, 0));
        System.out.println("inside custom service" + user);
        return user;
    }
}
