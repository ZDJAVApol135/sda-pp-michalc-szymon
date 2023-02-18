package com.sda.controller;

import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.service.UsersService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    public void findAll(){
        List<UserDTO> all = usersService.findAll();
        if (all.isEmpty()){
            System.out.println("Users list empty!");
        } else {
            System.out.println("Users list: ");
            all.forEach(System.out::println);
        }
    }

    public void findByUsername(String username){
        try {
            UserDTO user = usersService.findByUsername(username);
            System.out.println("User found: " + user);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
