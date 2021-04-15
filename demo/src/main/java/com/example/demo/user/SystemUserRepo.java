package com.example.demo.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SystemUserRepo extends CrudRepository<SystemUser, Integer> {
    Optional<SystemUser> findByUserName(String userName);
}
