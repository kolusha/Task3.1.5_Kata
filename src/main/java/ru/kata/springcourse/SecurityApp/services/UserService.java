package ru.kata.springcourse.SecurityApp.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.springcourse.SecurityApp.entities.Role;
import ru.kata.springcourse.SecurityApp.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserById(long id);

    boolean saveUser(User user);

    void deleteUserById(long id);


    void updateUser(User user);

    void saveRole(Role role);

    void saveUserRole(User user, Role role);

    List<Role> getAllRoles();

    List<User> getListOfUsers();
}
