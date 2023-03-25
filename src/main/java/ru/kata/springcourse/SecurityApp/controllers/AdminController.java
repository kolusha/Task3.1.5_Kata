package ru.kata.springcourse.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.springcourse.SecurityApp.entities.Role;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.services.UserService;
import ru.kata.springcourse.SecurityApp.util.MapperUserRole;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final MapperUserRole mapperUserRole;

    @Autowired
    public AdminController(UserService userService, MapperUserRole mapperUserRole) {
        this.userService = userService;
        this.mapperUserRole = mapperUserRole;
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("allUsers");

        return modelAndView;
    }

    @GetMapping()
    public ResponseEntity<Map<String ,Object>> printAllUsers() {
        Map<String, Object> getMap = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User admin = (User) auth.getPrincipal();
        List<Role> roles = userService.getAllRoles();

        List<User> users = userService.getListOfUsers();

        getMap.put("admin",admin);
        getMap.put("users",users);
        getMap.put("roles", roles);
        return new ResponseEntity<>(getMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUserById(@PathVariable("id") long id) {

       return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid User user) {
        userService.saveUser(user);

        return printAllUsers();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody User user) {
        userService.updateUser(user);

        return printAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable long id) {
        userService.deleteUserById(id);

        return printAllUsers();
    }
}
