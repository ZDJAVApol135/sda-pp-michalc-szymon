package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // tworzy konstruktory argumentowe tylko dla pól finalnych
public class UsersService {

    private final UsersDAO usersDAO;
    private final UserMapper userMapper;
}
