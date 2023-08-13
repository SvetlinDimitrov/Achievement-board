package com.example.achievementboard.domain.dtos.achievement;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AchievementCreate {
    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String name;

    private String descriptionHowItWasSucceeded;

    @NotNull(message = "You must fill the field")
    private LocalDate startDate;

    @NotNull(message = "You must fill the field")
    private LocalDate endDate;

    @NotNull(message = "You must fill the field")
    private Difficulty difficulty;

    private Long routineId;

}
