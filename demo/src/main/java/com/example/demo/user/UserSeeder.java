package com.example.demo.user;

import com.example.demo.security.entity.Authority;
import com.example.demo.security.repository.AuthorityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component @RequiredArgsConstructor @Log4j2
public class UserSeeder implements CommandLineRunner {
    private final SystemUserRepo systemUserRepo;
    private final AuthorityRepo authorityRepo;

    @Override
    public void run(String... args){
        log.info("Adding new users.....");

        Authority adminAuthority = new Authority(new HashSet<>(), "ROLE_ADMIN");
        Authority userAuthority = new Authority(new HashSet<>(), "ROLE_USER");

        SystemUser admin = new SystemUser("admin name", "testPass", adminAuthority, true);
        SystemUser user = new SystemUser("user name", "testPass", userAuthority, true);

        authorityRepo.saveAll(Arrays.asList(adminAuthority, userAuthority));
        systemUserRepo.saveAll(Arrays.asList(admin, user));
    }
}
