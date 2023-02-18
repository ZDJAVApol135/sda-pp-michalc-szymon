package com.sda.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@RequiredArgsConstructor
@Slf4j
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
        String ageStr = getString("Enter age: ");
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            log.error("Invalid age input! Defaulting to 0.");
            return 0;
        }
    }

    public String getEmail() {
        return getString("Enter email: ");
    }

    public String getPassword() {
        return getString("Enter password: ");
    }
}
