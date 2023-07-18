package com.example.achievementboard.entity;

import com.example.achievementboard.constants.BaseEntity;
import com.example.achievementboard.constants.Category;
import com.example.achievementboard.constants.Difficulty;
import com.example.achievementboard.constants.Importance;
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
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private String descriptionWhyYouWantToAchieveIt;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.MERGE)
    private Routine routine;
}
