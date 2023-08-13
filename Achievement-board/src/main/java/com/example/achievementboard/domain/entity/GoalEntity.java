package com.example.achievementboard.domain.entity;

import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
}
