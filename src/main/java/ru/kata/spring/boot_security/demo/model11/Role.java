//package ru.kata.spring.boot_security.demo.model;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//@Data
//public class Role implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String role;
//
//    public Role(String role) {
//        this.role = role;
//    }
//
//    public Role() {
//    }
//
//    @Override
//    public String getAuthority() {
//        return role;
//    }
//
//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<User>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Role role = (Role) o;
//        return Objects.equals(role, role.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(role);
//    }
//    @Override
//    public String toString() {
//        return role;
//    }
//}
