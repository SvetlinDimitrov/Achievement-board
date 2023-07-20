package com.example.achievementboard.entity;

import com.example.achievementboard.constants.dtos.goal.GoalView;
import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.constants.enums.Importance;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal extends BaseEntity {
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
    private User user;

    @ManyToOne
    private Routine routine;

    @Column
    private String pictureRes;

    public GoalView toGoalView(){
        return GoalView.builder()
                .id(getId())
                .name(name)
                .importance(importance)
                .descriptionHowToDoIt(descriptionHowToDoIt)
                .endDate(endDate)
                .beginDate(beginDate)
                .category(category)
                .descriptionWhyYouWantToAchieveIt(descriptionWhyYouWantToAchieveIt)
                .difficulty(difficulty)
                .routineId(routine == null ? 0: routine.getId())
                .build();
    }
}
