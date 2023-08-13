package com.example.achievementboard.domain.dtos.goal;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
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
public class GoalChange {
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
