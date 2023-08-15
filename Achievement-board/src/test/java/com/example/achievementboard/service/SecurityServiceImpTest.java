package com.example.achievementboard.service;

import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.UserRepository;
import com.example.achievementboard.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImpTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private SecurityServiceImp securityServiceImp;

    private UserEntity user;
    private UserView userView;

    @BeforeEach
    void setUp() {
        user = UserEntity.builder()
                .username("test")
                .email("test@abv.bg")
                .age(17)
                .birthDate(LocalDate.parse("2019-02-02"))
                .password("test")
                .build();
        user.setId(1L);

        userView = new UserView(user);
    }

    @Test
    void loadUserByUsername_validUsername_Successful() {
        when(userService.getByEmail(user.getEmail())).thenReturn(userView);

        UserDetails result = securityServiceImp.loadUserByUsername(userView.getEmail());

        assertEquals(userView, result);
    }

    @Test
    void loadUserByUsername_invalidUsername_throwsUsernameNotFoundException() {
        when(userService.getByEmail(user.getEmail())).thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> securityServiceImp.loadUserByUsername(userView.getEmail()));
    }
}