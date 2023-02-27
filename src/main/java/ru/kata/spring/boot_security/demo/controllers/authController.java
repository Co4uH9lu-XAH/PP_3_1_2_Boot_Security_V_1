package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
@RequestMapping("/auth")
public class authController {

    @GetMapping("/registration")
    public String RegistrationPage (@ModelAttribute ("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration (@ModelAttribute ("user") User user) {
        return ""; // TODO
    }
}
