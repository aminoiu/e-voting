package com.electronicvoting.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(String message, String email) {
        super(message);
        log.warn("User with e-mail [{}] is not registered.",email);
    }
}
