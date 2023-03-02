package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/auth")
public class authController {

    private final UserService userService;

    @Autowired
    public authController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String RegistrationPage (@ModelAttribute ("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration (@ModelAttribute ("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }
}
