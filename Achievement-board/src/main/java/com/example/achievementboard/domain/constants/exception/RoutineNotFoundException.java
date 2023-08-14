package com.example.achievementboard.domain.constants.exception;

public class RoutineNotFoundException extends Exception{
    
    public RoutineNotFoundException(Long id) {
        super("Routine with number: " + id +" does not existed");
    }
}
