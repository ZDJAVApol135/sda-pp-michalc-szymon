package com.sda.controller;

import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.model.User;
import com.sda.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Slf4j // Logger
public class UserController {
    //    private static final Logger log = Logger.
    private final UsersService usersService;

    public void findAll() {
        List<UserDTO> all = usersService.findAll();
        if (all.isEmpty()) {
            System.out.println("Users list empty!");
        } else {
            System.out.println("Users list: ");
            all.forEach(System.out::println);
        }
    }

    public void findByUsername(String username) {
        try {
            UserDTO userDTO = usersService.findByUsername(username);
            System.out.printf("User found: %s%n", userDTO);
        } catch (NotFoundException e) {
            log.error("NotFoundException: {}", e.getMessage());
        }
    }

    public void deleteByUsername(String username) {
        try {
            usersService.deleteByUsername(username);
            System.out.printf("User %s%n deleted!", username);
        } catch (NotFoundException e) {
            log.error("NotFoundException: {}", e.getMessage());
        }
    }
    public void create(User user){
        try {
            usersService.create(user);
            System.out.printf("User %s%n created!", user);
        } catch (UsernameConflictException e) {
            log.error("UsernameConflictException: {}", e.getMessage());
        }
    }

}
