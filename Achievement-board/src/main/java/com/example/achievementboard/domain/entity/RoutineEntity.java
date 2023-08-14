package com.example.achievementboard.domain.entity;

import com.example.achievementboard.domain.dtos.routine.RoutineChange;
import com.example.achievementboard.domain.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String descriptionHowToDoIt;

    @Column
    private Double hoursToSpend;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column
    @Enumerated(EnumType.STRING)
    private List<DaysOfTheWeek> days = new ArrayList<>();

    @ManyToOne
    private UserEntity userEntity;

    public RoutineChange toRoutineView() {
        return RoutineChange.builder()
                .id(getId())
                .name(name)
                .description(descriptionHowToDoIt)
                .hoursSpend(hoursToSpend)
                .difficulty(difficulty)
                .days(days)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineEntity that = (RoutineEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(descriptionHowToDoIt, that.descriptionHowToDoIt) && Objects.equals(hoursToSpend, that.hoursToSpend) && difficulty == that.difficulty && Objects.equals(days, that.days) && Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, descriptionHowToDoIt, hoursToSpend, difficulty, days, userEntity);
    }
}
