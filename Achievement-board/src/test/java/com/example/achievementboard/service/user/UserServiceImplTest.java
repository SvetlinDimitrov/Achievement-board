package com.example.achievementboard.service.user;

import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.user.RegisterUser;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private  UserRepository repository;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<List<UserEntity>> listArgumentCaptor;
    @Captor
    private ArgumentCaptor<UserEntity> argumentCaptor;

    private UserEntity user;
    private UserView userView;
    private List<UserEntity> users;
    private final Long INVALID_ID = 99L;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .username("test")
                .email("test@abv.bg")
                .age(4)
                .birthDate(LocalDate.parse("2019-02-02"))
                .password("test")
                .build();
        user.setId(1L);

        userView = new UserView(user);

        users = List.of(
                UserEntity.builder()
                        .username("test")
                        .email("test@abv.bg")
                        .age(4)
                        .birthDate(LocalDate.parse("2019-02-02"))
                        .password("test")
                        .build(),
                UserEntity.builder()
                        .username("test2")
                        .email("test2@abv.bg")
                        .age(4)
                        .birthDate(LocalDate.parse("2019-02-02"))
                        .password("test2")
                        .build(),
                UserEntity.builder()
                        .username("test3")
                        .email("test3@abv.bg")
                        .age(4)
                        .birthDate(LocalDate.parse("2019-02-02"))
                        .password("test3")
                        .build()
        );
    }

    @Test
    void saveAll_withoutChangingTheEntity_Successful() {
        userService.saveAll(users);

        verify(repository , times(1)).saveAll(listArgumentCaptor.capture());

        List<UserEntity> result = listArgumentCaptor.getValue();

        assertEquals(users,result);
    }

    @Test
    void getById_validId_successful() throws UserNotFoundException {
        Long VALID_ID = 1L;
        when(repository.findById(VALID_ID)).thenReturn(Optional.ofNullable(user));

        UserEntity result = userService.getById(VALID_ID);

        assertEquals(user , result);
    }

    @Test
    void getById_invalidId_throwsUserNotFoundException(){
        when(repository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class , () -> userService.getById(INVALID_ID));
    }

    @Test
    void save_withoutChangingTheEntity_successful() {
        userService.save(user);

        verify(repository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(user , result);
    }

    @Test
    void notUsedEmail_withNotUsedEmail_true() {
        String NOT_USED_EMAIL = "";
        when(repository.findByEmail(NOT_USED_EMAIL)).thenReturn(Optional.empty());

        assertTrue(userService.notUsedEmail(NOT_USED_EMAIL));
    }
    @Test
    void notUsedEmail_withUsedEmail_false() {
        String USED_EMAIL = "";
        when(repository.findByEmail(USED_EMAIL)).thenReturn(Optional.ofNullable(user));

        assertFalse(userService.notUsedEmail(USED_EMAIL));
    }

    @Test
    void saveUser_convertingToUserEntity_successful() {
        RegisterUser registerUser = new RegisterUser("test" , "test@abv.bg" , LocalDate.parse("2019-02-02") , "test");
        when(passwordEncoder.encode(registerUser.getPassword())).thenReturn("test");

        userService.saveUser(registerUser);

        verify(repository , times(1)).save(argumentCaptor.capture());

        UserEntity result = argumentCaptor.getValue();

        assertEquals(user , result);
    }

    @Test
    void getByEmail_validEmail_successful() {
        String VALID_EMAIL = "something";

        when(repository.findByEmail(VALID_EMAIL)).thenReturn(Optional.ofNullable(user));

        UserView result = userService.getByEmail(VALID_EMAIL);

        assertEquals(userView , result);
    }
    @Test
    void getByEmail_invalidEmail_throwsUsernameNotFoundException() {
        String INVALID_EMAIL = "something";

        when(repository.findByEmail(INVALID_EMAIL)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class , () -> userService.getByEmail(INVALID_EMAIL));
    }
}