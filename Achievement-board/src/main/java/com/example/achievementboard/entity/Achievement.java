package com.example.achievementboard.entity;

import com.example.achievementboard.constants.dtos.achievement.AchievementCreate;
import com.example.achievementboard.constants.dtos.achievement.AchievementView;
import com.example.achievementboard.constants.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Achievement extends BaseEntity {
    @Column
    private String name;

    @Column
    private String descriptionHowItWasSucceeded;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne
    private Routine routine;

    @Column
    private Long dayTook;

    @Column
    private String pictureRes;

    public AchievementView toDto() {
        return AchievementView.builder()
                .name(name)
                .descriptionHowItWasSucceeded(descriptionHowItWasSucceeded)
                .difficulty(difficulty)
                .startDate(startDate)
                .endDate(endDate)
                .routineId(routine == null ? 0 : routine.getId())
                .dayTook(dayTook)
                .id(getId())
                .build();

    }
}
