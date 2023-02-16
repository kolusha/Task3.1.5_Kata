package ru.kata.springcourse.SecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.services.UserService;

@Component
public class UserValidator implements Validator {
    final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    User user = (User) target;
    try {
        userService.loadUserByUsername(user.getUsername());
    } catch (UsernameNotFoundException e) {
        return;
    }
    errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }
}
