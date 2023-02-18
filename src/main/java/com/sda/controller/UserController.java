package com.sda.controller;

import com.sda.service.UsersService;

public class UserController {

    private final UsersService userService;

    public UserController(UsersService userService) {
        this.userService = userService;
    }
}
