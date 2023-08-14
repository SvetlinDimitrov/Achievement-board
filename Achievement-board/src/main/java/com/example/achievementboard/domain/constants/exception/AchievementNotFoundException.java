package com.example.achievementboard.domain.constants.exception;

public class AchievementNotFoundException extends Exception {
    public AchievementNotFoundException(Long id) {
        super("Achievement with id:"+id+ " does not exited");
    }
}
