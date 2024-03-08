package org.example.controller;

import org.example.domains.RegistrationForm;
import org.example.domains.User;
import org.example.repo.UserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registrationForm";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm){

        userRepo.save(registrationForm.toUser(passwordEncoder));

        return "redirect:/login";
    }
}
