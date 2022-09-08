package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class MainController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public MainController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    // загрузка главной страницы
    @GetMapping("/")
    public String welcomeUsers() {
        if (roleService.listRoles().isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
        }
        if (userService.listUsers().isEmpty()) {
            userService.addUser(new User("admin", passwordEncoder.encode("admin"),
                    "bob", "bobin", "bob@bob.com", roleService.findByName("ROLE_ADMIN")));
            userService.addUser(new User("user", passwordEncoder.encode("user"),
                    "Ivan", "Ivanov", "ivan@iavn.com", roleService.findByName("ROLE_USER")));

        }
        return "index";
    }

    // загрузка страницы пользователей
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("list", userService.listUsers());
        return "users";
    }

    // загрузка личной страницы пользователя
    @GetMapping("/user/{id}")
    public String userPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/user")
    public String userPageForUsername(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String adminPageForUsername(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

    // создание пользователя
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.listRoles());
        return "new";
    }

    @PostMapping()
    public String userCreate(@ModelAttribute("user") User user, @RequestParam(value = "role") String role) {
        user.setRoles(roleService.findByName(role));
        userService.addUser(user);
        return "redirect:/user";
    }

    //редактирование пользователя
    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }


    //удаление пользователя
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


}
