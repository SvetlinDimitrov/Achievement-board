package com.example.achievementboard.constants.dtos.achievement;

import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.entity.Achievement;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NotNull
@Builder
public class AchievementView {
    private Long id;
    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String name;

    private String descriptionHowItWasSucceeded;


    @NotNull(message = "You must fill the field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    @NotNull(message = "You must fill the field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "You must fill the field")
    private Difficulty difficulty;

    private Long routineId;

    private Long dayTook;

}
