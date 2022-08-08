package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Transient
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public String toString() {
        return role;
    }
}
