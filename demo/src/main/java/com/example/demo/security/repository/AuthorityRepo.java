package com.example.demo.security.repository;

import com.example.demo.security.entity.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepo extends CrudRepository<Authority, Integer> {
}
