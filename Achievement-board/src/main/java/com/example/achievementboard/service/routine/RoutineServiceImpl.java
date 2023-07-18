package com.example.achievementboard.service.routine;

import com.example.achievementboard.entity.Achievement;
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
    public Routine getById(Integer id) {
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
}
