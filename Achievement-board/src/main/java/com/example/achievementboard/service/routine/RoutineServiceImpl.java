package com.example.achievementboard.service.routine;

import com.example.achievementboard.constants.dtos.routine.RoutineCreate;
import com.example.achievementboard.constants.dtos.routine.RoutineView;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.repos.RoutineRepository;
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

    @Override
    public boolean isEmpty() {
        return routineRepository.count() == 0;
    }

    @Override
    public void saveAll(List<Routine> build) {
        routineRepository.saveAll(build);
    }

    @Override
    public Routine getById(Long id) {
        return routineRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Routine entity) {
        routineRepository.save(entity);
    }

    @Override
    public List<Routine> getAllRoutines(User user) {
        return routineRepository.findAllByUser(user);
    }

    @Override
    public List<Routine> getAllRoutinesSortedByDifficulty(User user) {
        List<Routine> allByUser = routineRepository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()));
        return allByUser;
    }

    @Override
    public List<Routine> getAllRoutinesSortByHourSpend(User user) {
        List<Routine> allByUser = routineRepository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getHoursToSpend().compareTo(g1.getHoursToSpend()));
        return allByUser;
    }

    @Override
    public void add(RoutineCreate createRoutine, User user) {
        Routine build = Routine.builder()
                .name(createRoutine.getName())
                .descriptionHowToDoIt(createRoutine.getDescription())
                .hoursToSpend(createRoutine.getHoursSpend())
                .difficulty(createRoutine.getDifficulty())
                .days(createRoutine.getDays())
                .user(user)
                .build();
        save(build);
    }

    @Override
    @Transactional
    public void deleteRoutine(Long id) {
        Routine routine = getById(id);

        goalRepository.findAllByRoutine(routine)
                        .forEach(g->g.setRoutine(null));

        achievementRepository.findAllByRoutine(routine)
                .forEach(a->a.setRoutine(null));

        routineRepository.deleteById(id);

    }

    @Override
    public void edit(RoutineView editedView) {
        Routine edit = getById(editedView.getId());

        edit.setName(editedView.getName());
        edit.setDescriptionHowToDoIt(editedView.getDescription());
        edit.setHoursToSpend(editedView.getHoursSpend());
        edit.setDifficulty(editedView.getDifficulty());
        edit.setDays(editedView.getDays());

        save(edit);
    }

    @Override
    public Routine getRandomRoutine() {
        return routineRepository.getRandomRoutine();
    }

}
