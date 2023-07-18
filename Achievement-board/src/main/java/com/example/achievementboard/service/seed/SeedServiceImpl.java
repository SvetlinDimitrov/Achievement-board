package com.example.achievementboard.service.seed;

import com.example.achievementboard.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.constants.enums.Importance;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.achievement.AchievementService;
import com.example.achievementboard.service.goal.GoalService;
import com.example.achievementboard.service.routine.RoutineService;
import com.example.achievementboard.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class SeedServiceImpl implements SeedService {

    private final UserService userService;
    private final GoalService goalService;
    private final AchievementService achievementService;
    private final RoutineService routineService;

    @Override
    public void seedUser() {
        if (userService.isEmpty()) {
            User build = User.builder()
                    .username("4orapa")
                    .email("4orapa@abv.bg")
                    .age(17)
                    .birthDate(LocalDate.now())
                    .password("4orapa")
                    .build();
            userService.save(build);

        }
    }

    @Override
    public void seedGoals() {
        if (goalService.isEmpty()) {
            goalService.saveAll(List.of(
                    Goal.builder()
                            .name("Clime Everest")
                            .importance(Importance.HIGH)
                            .descriptionHowToDoIt("i will walk 15k steps per day")
                            .descriptionWhyYouWantToAchieveIt("i want to be climber")
                            .endDate(LocalDate.parse("2026-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .user(userService.getById(1))
                            .difficulty(Difficulty.HARD)
                            .routine(routineService.getRouteByName("Walk"))
                            .build(),
                    Goal.builder()
                            .name("Get Jacked")
                            .importance(Importance.LOW)
                            .descriptionHowToDoIt("i will train 5 times a week")
                            .descriptionWhyYouWantToAchieveIt("i want to have a girl")
                            .endDate(LocalDate.parse("2025-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .user(userService.getById(1))
                            .difficulty(Difficulty.MEDIUM)
                            .routine(routineService.getRouteByName("Gym"))
                            .build(),
                    Goal.builder()
                            .name("Meditation")
                            .importance(Importance.MEDIUM)
                            .descriptionHowToDoIt("I want to reduce my stress")
                            .descriptionWhyYouWantToAchieveIt("i want to become one with the nature")
                            .endDate(LocalDate.parse("2024-12-27"))
                            .beginDate(LocalDate.now())
                            .category("sport")
                            .user(userService.getById(1))
                            .difficulty(Difficulty.EASY)
                            .routine(routineService.getRouteByName("Meditation"))
                            .build()

            ));
        }
    }

    @Override
    public void seedAchievement() {
        if (achievementService.isEmpty()) {
            achievementService.saveAll(List.of(
                    Achievement.builder()
                            .name("Maraton 20km")
                            .descriptionHowItWasSucceeded("ï was a beast and i just show it")
                            .startDate(LocalDate.parse("2022-12-27"))
                            .endDate(LocalDate.parse("2022-12-27"))
                            .routine(routineService.getRouteByName("Walk"))
                            .user(userService.getById(1))
                            .difficulty(Difficulty.MEDIUM)
                            .build(),
                    Achievement.builder()
                            .name("Meditation 1hour")
                            .descriptionHowItWasSucceeded("i was calm and relax , one with the floe")
                            .startDate(LocalDate.parse("2021-11-23"))
                            .endDate(LocalDate.parse("2021-11-23"))
                            .routine(routineService.getRouteByName("Meditation"))
                            .user(userService.getById(1))
                            .difficulty(Difficulty.MEDIUM)
                            .build(),
                    Achievement.builder()
                            .name("Six Pack")
                            .descriptionHowItWasSucceeded("never skip core day")
                            .startDate(LocalDate.parse("2023-01-27"))
                            .endDate(LocalDate.parse("2023-12-27"))
                            .routine(routineService.getRouteByName("Gym"))
                            .user(userService.getById(1))
                            .difficulty(Difficulty.HARD)
                            .build()
            ));


        }
    }

    @Override
    public void seedRoutine() {
        if (routineService.isEmpty()) {
            routineService.saveAll(List.of(
                    Routine.builder()
                            .name("Meditation")
                            .descriptionHowToDoIt("meditate 20 min every morning")
                            .hoursToSpend(0.20)
                            .difficulty(Difficulty.EASY)
                            .user(userService.getById(1))
                            .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                            .build(),
                    Routine.builder()
                            .name("Gym")
                            .descriptionHowToDoIt("just gonna lift")
                            .hoursToSpend(1.4)
                            .difficulty(Difficulty.MEDIUM)
                            .user(userService.getById(1))
                            .days(List.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY))
                            .build(),
                    Routine.builder()
                            .name("Walk")
                            .descriptionHowToDoIt("walk with 4km speed per hour")
                            .hoursToSpend(0.20)
                            .difficulty(Difficulty.EASY)
                            .user(userService.getById(1))
                            .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                            .build()

            ));
        }
    }

    @Override
    public void seedAll() {
        seedUser();
        seedRoutine();
        seedGoals();
        seedAchievement();
    }
}
