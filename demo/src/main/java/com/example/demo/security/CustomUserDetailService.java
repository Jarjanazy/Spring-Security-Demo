package com.example.demo.security;

import com.example.demo.user.SystemUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final SystemUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.
                findByUserName(userName).
                map(CustomUserDetails::new).
                orElseThrow(() -> new BadCredentialsException("Username or password are incorrect"));
    }
}
