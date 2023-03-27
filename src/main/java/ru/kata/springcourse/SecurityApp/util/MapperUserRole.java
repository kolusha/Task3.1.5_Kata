package ru.kata.springcourse.SecurityApp.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.springcourse.SecurityApp.dto.UserDTO;
import ru.kata.springcourse.SecurityApp.entities.User;

@Component
public class MapperUserRole {

    private final ModelMapper modelMapper;

    public MapperUserRole(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public  User convertToUser(UserDTO user) {
        return modelMapper.map(user, User.class);
    }
}
