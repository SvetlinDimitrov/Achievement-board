package com.example.achievementboard.constants.dtos.goal;

import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.constants.enums.Importance;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalView {
    private Long id;
    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String name;

    @NotNull(message = "You must fill the field")
    private Importance importance;

    private String descriptionHowToDoIt;

    @Future
    @NotNull(message = "You must fill the field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "You must fill the field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @NotBlank(message = "You must fill the field")
    @Size(min = 3)
    private String category;

    private String descriptionWhyYouWantToAchieveIt;

    @NotNull(message = "You must fill the field")
    private Difficulty difficulty;

    private Long routineId;
}
