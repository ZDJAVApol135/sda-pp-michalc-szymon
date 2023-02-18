package com.sda.controller;

import com.sda.dto.UserDTO;
import com.sda.service.UsersService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserController {

    private final UsersService userService;

    public void findAll(){
        List<UserDTO> all = userService.findAll();
        if (all.isEmpty()){
            System.out.println("Users list empty!");
        }
        System.out.println(all.stream());
    }

    public void findByUsername(String username){

    }
}
