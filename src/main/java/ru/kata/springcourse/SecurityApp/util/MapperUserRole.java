package ru.kata.springcourse.SecurityApp.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUserRole {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperUserRole(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
