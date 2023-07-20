package com.example.achievementboard.service.routine;

import com.example.achievementboard.constants.dtos.goal.GoalView;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.repos.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<Routine> build) {
        repository.saveAll(build);
    }

    @Override
    public Routine getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Routine entity) {
        repository.save(entity);
    }

    @Override
    public Routine getRouteByName(String walking) {
        return repository.findByName(walking);
    }

    @Override
    public List<Routine> getAllRoutines(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Routine> getAllRoutinesSortedByDifficulty(User user) {
        List<Routine> allByUser = repository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()));
        return allByUser;
    }

    @Override
    public List<Routine> getAllRoutinesSortByHourSpend(User user) {
        List<Routine> allByUser = repository.findAllByUser(user);
        allByUser.sort((g1,g2) -> g2.getHoursToSpend().compareTo(g1.getHoursToSpend()));
        return allByUser;
    }

}
