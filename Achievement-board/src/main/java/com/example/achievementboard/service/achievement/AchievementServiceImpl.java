package com.example.achievementboard.service.achievement;

import com.example.achievementboard.domain.dtos.achievement.AchievementCreate;
import com.example.achievementboard.domain.dtos.achievement.AchievementChange;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.dtos.achievement.AchievementView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import com.example.achievementboard.service.routine.RoutineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;

    @Override
    public boolean isEmpty() {
        return achievementRepository.count() == 0;
    }

    @Override
    public void saveAll(List<AchievementEntity> build) {
        build.forEach(this::fillAchievement);
        achievementRepository.saveAll(build);
    }

    @Override
    public AchievementEntity getById(Long id) {
        return achievementRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(AchievementEntity entity) {
        fillAchievement(entity);
        achievementRepository.save(entity);
    }

    @Override
    public List<AchievementView> getAllAchievementsSortedByTimeTook(Long userId) {
        return achievementRepository.findAllByUserEntity_Id(userId)
                .stream()
                .map(AchievementView::new)
                .sorted((a1, a2) -> a2.getDayTook().compareTo(a1.getDayTook()))
                .toList();
    }

    @Override
    public List<AchievementView> getAllAchievementsSortedByDifficulty(Long userId) {
        return achievementRepository.findAllByUserEntity_Id(userId)
                .stream()
                .map(AchievementView::new)
                .sorted((a1, a2) -> a2.getDifficulty().compareTo(a1.getDifficulty()))
                .toList();
    }

    @Override
    public void save(AchievementCreate achievementCreate, Long userId) {
        AchievementEntity achievementEntity = AchievementEntity.builder()
                .name(achievementCreate.getName())
                .descriptionHowItWasSucceeded(achievementCreate.getDescriptionHowItWasSucceeded())
                .startDate(achievementCreate.getStartDate())
                .endDate(achievementCreate.getEndDate())
                .difficulty(achievementCreate.getDifficulty())
                .userEntity(userRepository.findById(userId).orElseThrow())
                .build();

        if (achievementCreate.getRoutineId() != 0) {
            achievementEntity.setRoutineEntity(routineRepository.findById(achievementCreate.getRoutineId()).orElseThrow());
        }

        save(achievementEntity);
    }

    @Override
    public void edit(AchievementChange achievementChange) {
        AchievementEntity achievementEntityToEdit = getById(achievementChange.getId());
        achievementEntityToEdit.setName(achievementChange.getName());
        achievementEntityToEdit.setDescriptionHowItWasSucceeded(achievementChange.getDescriptionHowItWasSucceeded());
        achievementEntityToEdit.setStartDate(achievementChange.getStartDate());
        achievementEntityToEdit.setEndDate(achievementChange.getEndDate());
        achievementEntityToEdit.setDifficulty(achievementChange.getDifficulty());

        achievementEntityToEdit.setRoutineEntity(achievementChange.getRoutineId() == 0 ?
                null : routineRepository.findById(achievementChange.getRoutineId()).orElseThrow());

        save(achievementEntityToEdit);
    }

    @Override
    public void deleteAch(Long l) {
        achievementRepository.deleteById(l);
    }

    @Override
    public List<AchievementView> getAllAchievementsView(Long id) {
        return achievementRepository.findAllByUserEntity_Id(id).stream().map(AchievementView::new).toList();
    }


    private void fillAchievement(AchievementEntity entity) {
        long difference = ChronoUnit.DAYS.between(entity.getStartDate(), entity.getEndDate());
        entity.setDayTook(difference);
        if (entity.getDifficulty().equals(Difficulty.EASY)) {
            entity.setPictureRes("/pic/badgeRank3.png");
        } else if (entity.getDifficulty().equals(Difficulty.MEDIUM)) {
            entity.setPictureRes("/pic/badgeRank2.png");
        } else {
            entity.setPictureRes("/pic/badgeRank1.png");
        }
    }
}
