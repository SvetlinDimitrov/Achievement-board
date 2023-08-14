package com.example.achievementboard.service.user;

import com.example.achievementboard.domain.dtos.user.RegisterUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public void saveAll(List<UserEntity> build) {
        repository.saveAll(build);
    }

    @Override
    @Transactional
    public UserEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(UserEntity entity) {
        repository.save(entity);
    }

    @Override
    public boolean notUsedEmail(String value) {
        return repository.findByEmail(value).isEmpty();
    }

    @Override
    public void saveUser(RegisterUser user) {
        repository.save(user.toUser());
    }


    @Override
    public UserView getByEmail(String email) {
        return new UserView(repository.findByEmail(email).orElseThrow());
    }
}
