package com.example.demo.security.entity;

import com.example.demo.user.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data @NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String role;

    @OneToMany
    private Set<SystemUser> users;

    public Authority(Set<SystemUser> users, String role) {
        this.role = role;
        this.users = users;
    }
}
