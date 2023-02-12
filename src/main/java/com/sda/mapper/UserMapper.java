package com.sda.mapper;

import com.sda.dto.UserDTO;
import com.sda.model.User;

public class UserMapper {

    public UserDTO map(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .age(user.getAge())
                .name(user.getName())
                .email(user.getEmail())
                .surname(user.getSurname())
                .build();
    }
}
