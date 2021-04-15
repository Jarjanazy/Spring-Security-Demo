package com.example.demo.security.controller;


import com.example.demo.security.DTO.AuthenticationRequest;
import com.example.demo.security.DTO.AuthenticationResponse;
import com.example.demo.security.service.CustomUserDetailService;
import com.example.demo.security.service.JwtService;
import com.example.demo.user.SystemUser;
import com.example.demo.user.SystemUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailService;
    private final SystemUserRepo userRepo;
    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> verifyAndCreateAuthToken(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            verifyAuthenticationRequest(authenticationRequest);
            return createAuthToken(authenticationRequest);

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username or Password");
        }catch (RuntimeException re){
            return ResponseEntity.badRequest().body(String.format("The username %s isn't found", authenticationRequest.getUserName()));
        }
    }

    private ResponseEntity<AuthenticationResponse> createAuthToken(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails =  customUserDetailService.loadUserByUsername(authenticationRequest.getUserName());

        SystemUser user = userRepo.
                findByUserName(userDetails.getUsername()).
                orElseThrow(() -> new RuntimeException(""));

        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getUserName()));
    }
    // if this method runs successfully it means that authentication done successfully
    private void verifyAuthenticationRequest(AuthenticationRequest ar) {
        var authentication = new UsernamePasswordAuthenticationToken(ar.getUserName(), ar.getPassword());
        authenticationManager.authenticate(authentication);
    }

}
