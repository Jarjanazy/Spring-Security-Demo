package com.example.demo.user;

import com.example.demo.security.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class SystemUser {
    @Id
    private Integer id;

    @Column(unique = true)
    private String userName;

    private String password;

    @ManyToOne
    private Authority authority;

    private boolean enabled = true;

}
