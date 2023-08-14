package com.example.achievementboard.domain.dtos.goal;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import com.example.achievementboard.domain.entity.GoalEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalView goalView = (GoalView) o;
        return Objects.equals(id, goalView.id) && Objects.equals(name, goalView.name) && importance == goalView.importance && Objects.equals(descriptionHowToDoIt, goalView.descriptionHowToDoIt) && Objects.equals(endDate, goalView.endDate) && Objects.equals(beginDate, goalView.beginDate) && Objects.equals(category, goalView.category) && Objects.equals(descriptionWhyYouWantToAchieveIt, goalView.descriptionWhyYouWantToAchieveIt) && difficulty == goalView.difficulty && Objects.equals(userId, goalView.userId) && Objects.equals(routineId, goalView.routineId) && Objects.equals(pictureRes, goalView.pictureRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, importance, descriptionHowToDoIt, endDate, beginDate, category, descriptionWhyYouWantToAchieveIt, difficulty, userId, routineId, pictureRes);
    }
}
