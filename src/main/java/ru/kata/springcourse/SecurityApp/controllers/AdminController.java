package ru.kata.springcourse.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.springcourse.SecurityApp.dto.UserDTO;
import ru.kata.springcourse.SecurityApp.entities.Role;
import ru.kata.springcourse.SecurityApp.entities.User;
import ru.kata.springcourse.SecurityApp.services.UserService;
import ru.kata.springcourse.SecurityApp.util.MapperUserRole;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        UserDTO admin = mapperUserRole.convertToUserDTO((User) auth.getPrincipal());
        List<Role> roles = userService.getAllRoles();

        List<UserDTO> users = userService.getListOfUsers().stream()
                .map(mapperUserRole::convertToUserDTO).collect(Collectors.toList());

        getMap.put("admin",admin);
        getMap.put("users",users);
        getMap.put("roles", roles);
        return new ResponseEntity<>(getMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> showUserById(@PathVariable("id") long id) {

       return new ResponseEntity<>(mapperUserRole.convertToUserDTO(userService.findUserById(id)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid User userDTO) {
        userService.saveUser(userDTO);

        return printAllUsers();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody UserDTO user) {
        userService.updateUser(mapperUserRole.convertToUser(user));

        return printAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable long id) {
        userService.deleteUserById(id);

        return printAllUsers();
    }
}
