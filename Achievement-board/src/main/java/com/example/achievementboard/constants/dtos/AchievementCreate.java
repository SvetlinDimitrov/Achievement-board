package com.example.achievementboard.constants.dtos;

import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.entity.Achievement;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementCreate {
    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String name;

    private String descriptionHowItWasSucceeded;

    @Future
    @NotNull(message = "You must fill the field")
    //TODO:be sure that startDate will be least then endDate
    private LocalDate startDate;

    @Future
    @NotNull(message = "You must fill the field")
    private LocalDate endDate;

    @NotNull(message = "You must fill the field")
    private Difficulty difficulty;

    private Integer routineId;

}
