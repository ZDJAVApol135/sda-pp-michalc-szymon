package com.sda.exception;

import com.sda.dto.UserDTO;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
