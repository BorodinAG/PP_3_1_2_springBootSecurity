package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.swing.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(Spring name);
}
