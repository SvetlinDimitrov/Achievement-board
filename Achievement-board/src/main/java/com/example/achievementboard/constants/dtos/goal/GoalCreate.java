package com.example.achievementboard.constants.dtos.goal;

import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.constants.enums.Importance;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GoalCreate {
    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String name;

    private String descriptionHowItWillBeDone;

    private String bonusDescription;

    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String category;

    @NotNull(message = "You must fill the field")
    private LocalDate startDate;

    @Future
    @NotNull(message = "You must fill the field")
    private LocalDate endDate;

    @NotNull(message = "You must fill the field")
    private Difficulty difficulty;

    @NotNull(message = "You must fill the field")
    private Importance importance;

    private Long routineId;

}
