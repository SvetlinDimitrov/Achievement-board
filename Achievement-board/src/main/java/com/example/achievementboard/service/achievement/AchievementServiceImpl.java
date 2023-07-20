package com.example.achievementboard.service.achievement;

import com.example.achievementboard.constants.dtos.achievement.AchievementCreate;
import com.example.achievementboard.constants.dtos.achievement.AchievementView;
import com.example.achievementboard.constants.enums.Difficulty;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.service.routine.RoutineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository repository;
    private final RoutineService routineService;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<Achievement> build) {
        build.forEach(this::fillAchievement);
        repository.saveAll(build);
    }

    @Override
    public Achievement getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Achievement entity) {
        fillAchievement(entity);
        repository.save(entity);
    }

    @Override
    public List<Achievement> getAllAchievements(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Achievement> getAllAchievementsSortedByTimeTook(User user) {
        List<Achievement> allAchievements = getAllAchievements(user);
        allAchievements.sort((a1,a2) -> a2.getDayTook().compareTo(a1.getDayTook()));
        return allAchievements;
    }

    @Override
    public List<Achievement> getAllAchievementsSortedByDifficulty(User user) {
        List<Achievement> allAchievements = getAllAchievements(user);
        allAchievements.sort((a1,a2) -> a2.getDifficulty().compareTo(a1.getDifficulty()));
        return allAchievements;
    }

    @Override
    public void save(AchievementCreate achievementCreate, User user) {
        Achievement achievement = Achievement.builder()
                .name(achievementCreate.getName())
                .descriptionHowItWasSucceeded(achievementCreate.getDescriptionHowItWasSucceeded())
                .startDate(achievementCreate.getStartDate())
                .endDate(achievementCreate.getEndDate())
                .difficulty(achievementCreate.getDifficulty())
                .user(user)
                .build();

        if(achievementCreate.getRoutineId() != 0){
            achievement.setRoutine(routineService.getById(achievementCreate.getRoutineId()));
        }

        save(achievement);
    }

    @Override
    public void edit(AchievementView achievementView) {
        Achievement achievementToEdit = getById(achievementView.getId());
        achievementToEdit.setName(achievementView.getName());
        achievementToEdit.setDescriptionHowItWasSucceeded(achievementView.getDescriptionHowItWasSucceeded());
        achievementToEdit.setStartDate(achievementView.getStartDate());
        achievementToEdit.setEndDate(achievementView.getEndDate());
        achievementToEdit.setDifficulty(achievementView.getDifficulty());

        achievementToEdit.setRoutine(achievementView.getRoutineId() == 0 ?
                null : routineService.getById(achievementView.getRoutineId()));

        save(achievementToEdit);
    }

    @Override
    public void deleteAch(Long l) {
        repository.deleteById(l);
    }

    @Override
    public List<Achievement> getAllAchievementsByRoutine(Routine routine) {
        return repository.findAllByRoutine(routine);
    }

    private void fillAchievement(Achievement entity) {
        long difference = ChronoUnit. DAYS. between(entity.getStartDate(),entity.getEndDate());
        entity.setDayTook(difference);
        if(entity.getDifficulty().equals(Difficulty.EASY)){
            entity.setPictureRes("/pic/badgeRank3.png");
        }else if(entity.getDifficulty().equals(Difficulty.MEDIUM)){
            entity.setPictureRes("/pic/badgeRank2.png");
        }else{
            entity.setPictureRes("/pic/badgeRank1.png");
        }
    }
}
