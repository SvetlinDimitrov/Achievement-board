package com.example.achievementboard.service.routine;

import com.example.achievementboard.domain.constants.enums.DaysOfTheWeek;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.routine.RoutineChange;
import com.example.achievementboard.domain.dtos.routine.RoutineCreate;
import com.example.achievementboard.domain.dtos.routine.RoutineView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoutineServiceImplTest {

    @Mock
    private RoutineRepository routineRepository;
    @Mock
    private GoalRepository goalRepository;
    @Mock
    private AchievementRepository achievementRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RoutineServiceImpl routineService;

    @Captor
    private ArgumentCaptor<RoutineEntity> argumentCaptor;
    @Captor
    private ArgumentCaptor<List<RoutineEntity>> listArgumentCaptor;

    private List<RoutineEntity> routineEntities;
    private List<RoutineView> routineViews;
    private RoutineCreate routineCreate;
    private RoutineChange routineChange;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = 99L;

    @BeforeEach
    void setUp() {
        UserEntity user = new UserEntity();
        UserEntity user2 = new UserEntity();
        UserEntity user3 = new UserEntity();
        user.setId(1L);
        user2.setId(2L);
        user3.setId(3L);

        routineEntities = List.of(
                RoutineEntity.builder()
                        .name("Meditation")
                        .descriptionHowToDoIt("meditate 20 min every morning")
                        .hoursToSpend(0.20)
                        .difficulty(Difficulty.EASY)
                        .userEntity(user)
                        .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                        .build(),
                RoutineEntity.builder()
                        .name("Gym")
                        .descriptionHowToDoIt("just gonna lift")
                        .hoursToSpend(1.4)
                        .difficulty(Difficulty.MEDIUM)
                        .userEntity(user2)
                        .days(List.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY))
                        .build(),
                RoutineEntity.builder()
                        .name("Walk")
                        .descriptionHowToDoIt("walk with 4km speed per hour")
                        .hoursToSpend(0.20)
                        .difficulty(Difficulty.EASY)
                        .userEntity(user3)
                        .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                        .build()
        );

        routineEntities.get(0).setId(1L);
        routineEntities.get(1).setId(2L);
        routineEntities.get(2).setId(3L);

        routineViews = List.of(
                RoutineView.builder()
                        .id(1L)
                        .name("Meditation")
                        .descriptionHowToDoIt("meditate 20 min every morning")
                        .hoursToSpend(0.20)
                        .difficulty(Difficulty.EASY)
                        .userId(1L)
                        .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                        .build(),
                RoutineView.builder()
                        .id(2L)
                        .name("Gym")
                        .descriptionHowToDoIt("just gonna lift")
                        .hoursToSpend(1.4)
                        .difficulty(Difficulty.MEDIUM)
                        .userId(2L)
                        .days(List.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY))
                        .build(),
                RoutineView.builder()
                        .id(3L)
                        .name("Walk")
                        .descriptionHowToDoIt("walk with 4km speed per hour")
                        .hoursToSpend(0.20)
                        .difficulty(Difficulty.EASY)
                        .userId(3L)
                        .days(Arrays.stream(DaysOfTheWeek.values()).toList())
                        .build()
        );
        routineCreate =
                RoutineCreate.builder()
                        .name(routineEntities.get(0).getName())
                        .description(routineEntities.get(0).getDescriptionHowToDoIt())
                        .hoursSpend(routineEntities.get(0).getHoursToSpend())
                        .difficulty(routineEntities.get(0).getDifficulty())
                        .days(routineEntities.get(0).getDays())
                        .build();

        routineChange =
                RoutineChange.builder()
                        .name(routineEntities.get(0).getName())
                        .description(routineEntities.get(0).getDescriptionHowToDoIt())
                        .hoursSpend(routineEntities.get(0).getHoursToSpend())
                        .difficulty(routineEntities.get(0).getDifficulty())
                        .days(routineEntities.get(0).getDays())
                        .build();

    }

    @Test
    void saveAll_savingCorrectly_Successful() {
        routineService.saveAll(routineEntities);

        verify(routineRepository, times(1)).saveAll(listArgumentCaptor.capture());

        List<RoutineEntity> result = listArgumentCaptor.getValue();

        assertEquals(routineEntities , result);
    }

    @Test
    void getById_validId_Successful() throws RoutineNotFoundException {
        RoutineEntity expected = routineEntities.get(0);
        when(routineRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(expected));

        RoutineEntity result = routineService.getById(VALID_ID);

        assertEquals(expected , result);
    }

    @Test
    void getById_invalidId_ThrowsRoutineNotFoundException() {
        when(routineRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class , () -> routineService.getById(INVALID_ID));
    }

    @Test
    void save_savingWithoutModifying_Successful() {
        RoutineEntity expected = routineEntities.get(0);

        routineService.save(expected);

        verify(routineRepository, times(1)).save(argumentCaptor.capture());

        RoutineEntity result = argumentCaptor.getValue();

        assertEquals(expected , result);
    }

    @Test
    void getAllRoutines_validIdAndConvertingToView_Successful() {
        when(routineRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(routineEntities);

        List<RoutineView> result = routineService.getAllRoutines(VALID_ID);

        assertEquals(routineViews , result);
    }
    @Test
    void getAllRoutines_invalidId_EmptyList() {
        when(routineRepository.findAllByUserEntity_Id(INVALID_ID)).thenReturn(List.of());

        List<RoutineView> result = routineService.getAllRoutines(INVALID_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllRoutinesSortedByDifficulty_validIdAndOrderChecking_Successful() {
        List<RoutineView> expected = routineViews.stream()
                .sorted((g1, g2) -> g2.getDifficulty().compareTo(g1.getDifficulty()))
                .toList();

        when(routineRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(routineEntities);

        List<RoutineView> result = routineService.getAllRoutinesSortedByDifficulty(VALID_ID);

        assertEquals(expected , result);
    }

    @Test
    void getAllRoutinesSortedByDifficulty_invalidId_EmptyList() {
        when(routineRepository.findAllByUserEntity_Id(INVALID_ID)).thenReturn(List.of());

        List<RoutineView> result = routineService.getAllRoutinesSortedByDifficulty(INVALID_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllRoutinesSortByHourSpend_validIdOrderChecking_Successful() {
        List<RoutineView> expected = routineViews.stream()
                .sorted((g1, g2) -> g2.getHoursToSpend().compareTo(g1.getHoursToSpend()))
                .toList();

        when(routineRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(routineEntities);

        List<RoutineView> result = routineService.getAllRoutinesSortByHourSpend(VALID_ID);

        assertEquals(expected , result);
    }
    @Test
    void getAllRoutinesSortByHourSpend_invalidId_EmptyList() {
        when(routineRepository.findAllByUserEntity_Id(INVALID_ID)).thenReturn(List.of());

        List<RoutineView> result = routineService.getAllRoutinesSortByHourSpend(INVALID_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void add_validUserId_Successful() throws UserNotFoundException {
        RoutineEntity expected = routineEntities.get(0);
        when(userRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(expected.getUserEntity()));

        routineService.add(routineCreate , VALID_ID);

        verify(routineRepository , times(1)).save(argumentCaptor.capture());

        RoutineEntity result = argumentCaptor.getValue();

        assertEquals(expected , result);
    }

    @Test
    void add_invalidUserId_ThrowsUserNotFoundException(){
        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class , () ->routineService.add(routineCreate , INVALID_ID));
    }

    @Test
    void deleteRoutine_testConnectionWithOtherEntities_Successful() {
        RoutineEntity toDelete = routineEntities.get(0);
        List<GoalEntity> goalEntities = List.of(
                GoalEntity.builder()
                        .routineEntity(toDelete)
                        .build(),
                GoalEntity.builder()
                        .routineEntity(toDelete)
                        .build(),
                GoalEntity.builder()
                        .routineEntity(toDelete)
                        .build()
                );
        List<AchievementEntity> achievementEntities = List.of(
                AchievementEntity.builder()
                        .routineEntity(toDelete)
                        .build(),
                AchievementEntity.builder()
                        .routineEntity(toDelete)
                        .build(),
                AchievementEntity.builder()
                        .routineEntity(toDelete)
                        .build()
        );

        when(goalRepository.findAll()).thenReturn(goalEntities);
        when(achievementRepository.findAll()).thenReturn(achievementEntities);

        routineService.deleteRoutine(toDelete.getId());

        verify(routineRepository , times(1)).deleteById(toDelete.getId());

        achievementEntities.forEach(a->assertNull(a.getRoutineEntity()));
        goalEntities.forEach(g-> assertNull(g.getRoutineEntity()));
    }

    @Test
    void edit_withValidRoutineId_Successful() throws RoutineNotFoundException {
        RoutineEntity routineToBeChange = routineEntities.get(1);
        RoutineEntity expected = routineEntities.get(0);

        when(routineRepository.findById(routineChange.getId())).thenReturn(Optional.ofNullable(routineToBeChange));

        routineService.edit(routineChange);

        verify(routineRepository , times(1)).save(argumentCaptor.capture());

        RoutineEntity result = argumentCaptor.getValue();
        assertEquals(expected , result);
    }

    @Test
    void edit_withInvalidRoutineId_ThrowsRoutineNotFoundException(){

        when(routineRepository.findById(routineChange.getId())).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class , () -> routineService.edit(routineChange));
    }
}