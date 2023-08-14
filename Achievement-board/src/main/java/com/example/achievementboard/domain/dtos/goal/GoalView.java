package com.example.achievementboard.domain.dtos.goal;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import com.example.achievementboard.domain.entity.GoalEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GoalView {

    private Long id;
    private String name;
    private Importance importance;
    private String descriptionHowToDoIt;
    private LocalDate endDate;
    private LocalDate beginDate;
    private String category;
    private String descriptionWhyYouWantToAchieveIt;
    private Difficulty difficulty;
    private Long userId;
    private Long routineId;
    private String pictureRes;

    public GoalView(GoalEntity goalEntity) {
        this.id = goalEntity.getId();
        this.name = goalEntity.getName();
        this.importance = goalEntity.getImportance();
        this.descriptionHowToDoIt = goalEntity.getDescriptionHowToDoIt();
        this.endDate = goalEntity.getEndDate();
        this.beginDate = goalEntity.getBeginDate();
        this.category = goalEntity.getCategory();
        this.descriptionWhyYouWantToAchieveIt = goalEntity.getDescriptionWhyYouWantToAchieveIt();
        this.difficulty = goalEntity.getDifficulty();
        this.userId = goalEntity.getUserEntity().getId();
        this.routineId = goalEntity.getRoutineEntity() == null ? null : goalEntity.getRoutineEntity().getId();
        this.pictureRes = goalEntity.getPictureRes();

    }
}
