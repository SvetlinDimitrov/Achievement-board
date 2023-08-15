package com.example.achievementboard.service.user;

import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.user.RegisterUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

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
    public UserEntity getById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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
        UserEntity userToSave = user.toUser();
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        repository.save(userToSave);
    }


    @Override
    @Transactional
    public UserView getByEmail(String email) {
        return new UserView(repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email)));
    }
}
