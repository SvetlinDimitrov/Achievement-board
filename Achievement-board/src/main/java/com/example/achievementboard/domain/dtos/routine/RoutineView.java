package com.example.achievementboard.domain.dtos.routine;

import com.example.achievementboard.domain.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.entity.RoutineEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineView {

    private Long id;
    private String name;
    private String descriptionHowToDoIt;
    private Double hoursToSpend;
    private Difficulty difficulty;
    private List<DaysOfTheWeek> days = new ArrayList<>();
    private Long userId;

    public RoutineView(RoutineEntity routineEntity) {
        this.id = routineEntity.getId();
        this.name = routineEntity.getName();
        this.descriptionHowToDoIt = routineEntity.getDescriptionHowToDoIt();
        this.hoursToSpend = routineEntity.getHoursToSpend();
        this.difficulty = routineEntity.getDifficulty();
        this.days = routineEntity.getDays();
        this.userId = routineEntity.getUserEntity().getId();
    }
}
