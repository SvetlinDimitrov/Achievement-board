package com.example.achievementboard.domain.constants.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(Long id) {
        super("User with the give id: " + id + " does not exited");
    }
}
