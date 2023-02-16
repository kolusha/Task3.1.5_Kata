package ru.kata.springcourse.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.services.UserService;
import ru.kata.springcourse.SecurityApp.util.UserValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/registration";

        userService.saveUser(user);

        return "redirect:/user";
    }
}
