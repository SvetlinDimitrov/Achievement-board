package com.example.achievementboard.domain.dtos.routine;

import com.example.achievementboard.domain.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.entity.RoutineEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineView that = (RoutineView) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(descriptionHowToDoIt, that.descriptionHowToDoIt) && Objects.equals(hoursToSpend, that.hoursToSpend) && difficulty == that.difficulty && Objects.equals(days, that.days) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, descriptionHowToDoIt, hoursToSpend, difficulty, days, userId);
    }
}
