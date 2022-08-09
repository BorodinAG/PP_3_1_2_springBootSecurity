//package ru.kata.spring.boot_security.demo.model;
//
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Entity
//@Table(name = "users")
//@Data
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//    private String password;
//    private String name;
//    private String surname;
//    private String email;
//
//    //    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return getRoles();
////    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "roles_id"))
//    private Set<Role> roles = new HashSet<Role>();
//
//    public void addRole(Role role) {
//        roles.add(role);
//        role.getUsers().add(this);
//    }
//
//    public void removeRole(Role role) {
//        roles.remove(role);
//        role.getUsers().remove(this);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        return id != null && id.equals(((User) o).getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//
//    public User(String username, String password, String name, String surname, String email, Set<Role> roles) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.roles = roles;
//    }
//
//    public User(String username, String password, String name, String surname, String email) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//    }
//
//    public User() {
//    }
//}
//
//
//
