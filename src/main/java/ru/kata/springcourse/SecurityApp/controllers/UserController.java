package ru.kata.springcourse.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.repositories.UserRepository;


@Controller
public class UserController {

    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("admin", user);
        return "/user";
    }
}
