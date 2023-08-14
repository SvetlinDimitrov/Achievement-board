package com.example.achievementboard.domain.dtos.achievement;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.entity.AchievementEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementView {

    private Long id;
    private String name;
    private String descriptionHowItWasSucceeded;
    private LocalDate startDate;
    private LocalDate endDate;
    private Difficulty difficulty;
    private Long userId;
    private Long routineId;
    private Long dayTook;
    private String pictureRes;

    public AchievementView(AchievementEntity achievement) {
        this.id = achievement.getId();
        this.name = achievement.getName();
        this.descriptionHowItWasSucceeded = achievement.getDescriptionHowItWasSucceeded();
        this.startDate = achievement.getStartDate();
        this.endDate = achievement.getEndDate();
        this.difficulty = achievement.getDifficulty();
        this.userId = achievement.getUserEntity().getId();
        this.routineId = achievement.getRoutineEntity() == null ? null : achievement.getRoutineEntity().getId();
        this.dayTook = achievement.getDayTook();
        this.pictureRes = achievement.getPictureRes();
    }
}
