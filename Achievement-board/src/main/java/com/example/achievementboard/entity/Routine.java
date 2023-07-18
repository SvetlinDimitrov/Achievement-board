package com.example.achievementboard.entity;

import com.example.achievementboard.constants.BaseEntity;
import com.example.achievementboard.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.constants.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Routine extends BaseEntity{

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
    private User user;

}
