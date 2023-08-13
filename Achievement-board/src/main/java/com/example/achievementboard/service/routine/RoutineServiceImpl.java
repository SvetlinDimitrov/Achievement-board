package com.example.achievementboard.service.routine;

import com.example.achievementboard.domain.dtos.routine.RoutineCreate;
import com.example.achievementboard.domain.dtos.routine.RoutineChange;
import com.example.achievementboard.domain.dtos.routine.RoutineView;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;
    private final GoalRepository goalRepository;
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @Override
    public boolean isEmpty() {
        return routineRepository.count() == 0;
    }

    @Override
    public void saveAll(List<RoutineEntity> build) {
        routineRepository.saveAll(build);
    }

    @Override
    public RoutineEntity getById(Long id) {
        return routineRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(RoutineEntity entity) {
        routineRepository.save(entity);
    }

    @Override
    public List<RoutineView> getAllRoutines(Long userId) {
        return routineRepository.findAllByUserEntity_Id(userId).stream().map(RoutineView::new).toList();
    }

    @Override
    public List<RoutineView> getAllRoutinesSortedByDifficulty(Long userId) {
        return routineRepository.findAllByUserEntity_Id(userId).stream()
                .map(RoutineView::new)
                .sorted((g1, g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()))
                .toList();
    }

    @Override
    public List<RoutineView> getAllRoutinesSortByHourSpend(Long userId) {
        return routineRepository.findAllByUserEntity_Id(userId).stream()
                .map(RoutineView::new)
                .sorted((g1, g2) -> g2.getHoursToSpend().compareTo(g1.getHoursToSpend()))
                .toList();
    }

    @Override
    public void add(RoutineCreate createRoutine, Long userId) {
        RoutineEntity build = RoutineEntity.builder()
                .name(createRoutine.getName())
                .descriptionHowToDoIt(createRoutine.getDescription())
                .hoursToSpend(createRoutine.getHoursSpend())
                .difficulty(createRoutine.getDifficulty())
                .days(createRoutine.getDays())
                .userEntity(userRepository.findById(userId).orElseThrow())
                .build();
        save(build);
    }

    @Override
    @Transactional
    public void deleteRoutine(Long id) {

        goalRepository.findAll()
                .stream()
                .filter(g -> g.getRoutineEntity().getId().equals(id))
                .forEach(g -> g.setRoutineEntity(null));

        achievementRepository.findAll()
                .stream()
                .filter(a -> a.getRoutineEntity().getId().equals(id))
                .forEach(a -> a.setRoutineEntity(null));

        routineRepository.deleteById(id);

    }

    @Override
    public void edit(RoutineChange editedView) {
        RoutineEntity edit = getById(editedView.getId());

        edit.setName(editedView.getName());
        edit.setDescriptionHowToDoIt(editedView.getDescription());
        edit.setHoursToSpend(editedView.getHoursSpend());
        edit.setDifficulty(editedView.getDifficulty());
        edit.setDays(editedView.getDays());

        save(edit);
    }

    @Override
    public RoutineEntity getRandomRoutine() {
        return routineRepository.getRandomRoutine();
    }

}
