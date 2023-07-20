package com.example.achievementboard.constants.dtos.routine;

import com.example.achievementboard.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.constants.enums.Difficulty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineCreate {

    @NotBlank(message = "you must fill this")
    @Size(min = 2, message = "must be at least 4 chars long")
    private String name;

    private String description;

    @NotNull(message = "you must fill this")
    @Positive
    @DecimalMin(value = "0.1")
    private Double hoursSpend;

    @NotNull(message = "you must fill this")
    private Difficulty difficulty;

    @NotEmpty(message = "you must choose at least 1 day")
    private List<DaysOfTheWeek> days = new ArrayList<>();
}
