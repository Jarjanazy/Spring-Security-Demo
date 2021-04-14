package com.example.demo.security;

import com.example.demo.user.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Authority {
    @Id
    private Integer id;

    @Column(unique = true)
    private String role;

    @OneToMany
    private Set<SystemUser> users;
}
