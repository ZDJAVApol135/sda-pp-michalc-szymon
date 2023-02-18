package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.mapper.UserMapper;
import com.sda.model.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor // tworzy konstruktory argumentowe tylko dla pól finalnych
public class UsersService {

    private final UsersDAO usersDAO;
    private final UserMapper userMapper;

    public List<UserDTO> findAll() {
//        List<User> users = usersDAO.findAll();
//        List<UserDTO> userDTOS = new ArrayList<>();
//
//        for (User user : users) {
//            UserDTO dto = userMapper.map(user);
//            userDTOS.add(dto);
//        }
//        return userDTOS;
        return usersDAO.findAll().stream()
                .map(user -> userMapper.map(user))
                .toList();
    }

    public UserDTO findByUsername(String username) {
        User user = usersDAO.findByUsername(username);

        if (user == null){
            String message = "User with username: '%s' not found".formatted(username);
            throw new NotFoundException(message);
        }
        UserDTO userDTO = userMapper.map(user);
        return userDTO;
    }

}
