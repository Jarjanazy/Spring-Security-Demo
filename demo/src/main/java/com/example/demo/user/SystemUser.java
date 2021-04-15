package com.example.demo.user;

import com.example.demo.security.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @Builder
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String userName;

    private String password;

    @ManyToOne
    private Authority authority;

    private boolean enabled = true;

    public SystemUser(String userName, String password, Authority authority, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.authority = authority;
        this.enabled = enabled;
    }
}
