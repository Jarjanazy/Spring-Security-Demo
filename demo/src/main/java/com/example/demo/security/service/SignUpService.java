package com.example.demo.security.service;

import com.example.demo.security.DTO.SignUpDTO;
import com.example.demo.security.repository.AuthorityRepo;
import com.example.demo.user.SystemUser;
import com.example.demo.user.SystemUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SystemUserRepo systemUserRepo;
    private final AuthorityRepo authorityRepo;

    public ResponseEntity<SystemUser> signUp(SignUpDTO signUpDTO) {
        return systemUserRepo.findByUserName(signUpDTO.getUserName()).
                map(this::createUsernameAlreadyTakenMessage).
                orElse(createNewUser(signUpDTO));
    }

    private ResponseEntity<SystemUser> createUsernameAlreadyTakenMessage(SystemUser user) {
        return ResponseEntity.
                status(HttpStatus.CONFLICT).
                body(user);
    }

    private ResponseEntity<SystemUser> createNewUser(SignUpDTO signUpDTO) {
        SystemUser newUser = createUserFromSignUpDTO(signUpDTO);
        systemUserRepo.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    private SystemUser createUserFromSignUpDTO(SignUpDTO signUpDTO) {
        return SystemUser.
                builder().
                userName(signUpDTO.getUserName()).
                password(signUpDTO.getPassword()).
                authority(authorityRepo.findByRole("ROLE_USER")).
                enabled(false).
                build();
    }

}
