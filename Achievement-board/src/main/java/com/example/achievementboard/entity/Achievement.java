package com.example.achievementboard.entity;

import com.example.achievementboard.constants.BaseEntity;
import com.example.achievementboard.constants.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Achievement extends BaseEntity {
    @Column
    private String name;

    @Column
    private String descriptionHowItWasSucceeded;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne
    private Routine routine;

}
