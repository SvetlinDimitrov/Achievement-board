package com.example.achievementboard.domain.constants.exception;

public class GoalNotFoundException extends Exception{
    public GoalNotFoundException(Long id) {
        super("Goal with the give id: " + id+ "does not exited");
    }
}
