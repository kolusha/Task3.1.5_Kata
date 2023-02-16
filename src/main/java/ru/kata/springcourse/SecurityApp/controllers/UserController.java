package ru.kata.springcourse.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.repositories.UserRepository;

import java.security.Principal;

@Controller
public class UserController {
    final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/user")
public String userInfo(Model model, Principal principal) {
        User user = (User) userRepository.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("userInfo", user);
        return "users/userPage";
}
}
