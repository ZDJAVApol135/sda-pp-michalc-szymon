package com.sda.controller;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class InputController {

    private final Scanner scanner = new Scanner(System.in);

    public String getString(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }

    public String getUsername() {
        return getString("Enter username: ");
    }

    public String getName() {
        return getString("Enter name: ");
    }

    public String getSurname() {
        return getString("Enter surname: ");
    }

    public int getAge() {
        return Integer.parseInt(getString("Enter age: "));
    }

    public String getEmail() {
        return getString("Enter email: ");
    }

    public String getPassword() {
        return getString("Enter password: ");
    }
}
