package com.example.achievementboard.domain.entity;

import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Importance importance;

    @Column
    private String descriptionHowToDoIt;

    @Column
    private LocalDate endDate;

    @Column
    private LocalDate beginDate;

    @Column
    private String category;

    @Column
    private String descriptionWhyYouWantToAchieveIt;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private RoutineEntity routineEntity;

    @Column
    private String pictureRes;

    public GoalChange toGoalView(){
        return GoalChange.builder()
                .id(getId())
                .name(name)
                .importance(importance)
                .descriptionHowToDoIt(descriptionHowToDoIt)
                .endDate(endDate)
                .beginDate(beginDate)
                .category(category)
                .descriptionWhyYouWantToAchieveIt(descriptionWhyYouWantToAchieveIt)
                .difficulty(difficulty)
                .routineId(routineEntity == null ? 0: routineEntity.getId())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalEntity that = (GoalEntity) o;
        return Objects.equals(name, that.name) && importance == that.importance && Objects.equals(descriptionHowToDoIt, that.descriptionHowToDoIt) && Objects.equals(endDate, that.endDate) && Objects.equals(beginDate, that.beginDate) && Objects.equals(category, that.category) && Objects.equals(descriptionWhyYouWantToAchieveIt, that.descriptionWhyYouWantToAchieveIt) && difficulty == that.difficulty && Objects.equals(userEntity, that.userEntity) && Objects.equals(routineEntity, that.routineEntity) && Objects.equals(pictureRes, that.pictureRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, importance, descriptionHowToDoIt, endDate, beginDate, category, descriptionWhyYouWantToAchieveIt, difficulty, userEntity, routineEntity, pictureRes);
    }
}
