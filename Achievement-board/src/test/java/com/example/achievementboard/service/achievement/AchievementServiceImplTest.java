package com.example.achievementboard.service.achievement;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.exception.AchievementNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.achievement.AchievementChange;
import com.example.achievementboard.domain.dtos.achievement.AchievementCreate;
import com.example.achievementboard.domain.dtos.achievement.AchievementView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.AchievementRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AchievementServiceImplTest {

    @Mock
    private AchievementRepository achievementRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoutineRepository routineRepository;
    @InjectMocks
    private AchievementServiceImpl achievementService;

    @Captor
    private ArgumentCaptor<List<AchievementEntity>> listArgumentCaptor;
    @Captor
    private ArgumentCaptor<AchievementEntity> argumentCaptor;

    private final List<AchievementEntity> achievementEntityList = new ArrayList<>();
    private final List<AchievementEntity> achievementEntityBeforeCreatinList = new ArrayList<>();
    private final Long SEARCH_ID = 1L;
    private UserEntity user;
    private RoutineEntity routineEntity;

    @BeforeEach
    void setUp() {
        routineEntity = new RoutineEntity();
        routineEntity.setId(1L);
        RoutineEntity routineEntity2 = new RoutineEntity();
        routineEntity2.setId(2L);
        RoutineEntity routineEntity3 = new RoutineEntity();
        routineEntity3.setId(3L);

        user = new UserEntity();
        user.setId(1L);
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        UserEntity user3 = new UserEntity();
        user3.setId(3L);

        achievementEntityList.addAll(List.of(
                AchievementEntity.builder()
                        .name("Maraton 20km")
                        .descriptionHowItWasSucceeded("ï was a beast and i just show it")
                        .startDate(LocalDate.parse("2022-09-22"))
                        .endDate(LocalDate.parse("2022-12-27"))
                        .routineEntity(routineEntity)
                        .userEntity(user)
                        .difficulty(Difficulty.MEDIUM)
                        .pictureRes("/pic/badgeRank2.png")
                        .build(),
                AchievementEntity.builder()
                        .name("Meditation 1hour")
                        .descriptionHowItWasSucceeded("i was calm and relax , one with the floe")
                        .startDate(LocalDate.parse("2021-11-01"))
                        .endDate(LocalDate.parse("2021-11-23"))
                        .routineEntity(routineEntity2)
                        .userEntity(user2)
                        .difficulty(Difficulty.EASY)
                        .pictureRes("/pic/badgeRank3.png")
                        .build(),
                AchievementEntity.builder()
                        .name("Six Pack")
                        .descriptionHowItWasSucceeded("never skip core day")
                        .startDate(LocalDate.parse("2023-01-27"))
                        .endDate(LocalDate.parse("2023-12-27"))
                        .routineEntity(routineEntity3)
                        .userEntity(user3)
                        .difficulty(Difficulty.HARD)
                        .pictureRes("/pic/badgeRank1.png")
                        .build()
        ));
        achievementEntityList.get(0).setDayTook(ChronoUnit.DAYS.between(achievementEntityList.get(0).getStartDate(), achievementEntityList.get(0).getEndDate()));
        achievementEntityList.get(1).setDayTook(ChronoUnit.DAYS.between(achievementEntityList.get(1).getStartDate(), achievementEntityList.get(1).getEndDate()));
        achievementEntityList.get(2).setDayTook(ChronoUnit.DAYS.between(achievementEntityList.get(2).getStartDate(), achievementEntityList.get(2).getEndDate()));

        achievementEntityList.get(0).setId(1L);
        achievementEntityList.get(1).setId(2L);
        achievementEntityList.get(2).setId(3L);


        achievementEntityBeforeCreatinList.addAll(List.of(
                AchievementEntity.builder()
                        .name("Maraton 20km")
                        .descriptionHowItWasSucceeded("ï was a beast and i just show it")
                        .startDate(LocalDate.parse("2022-09-22"))
                        .endDate(LocalDate.parse("2022-12-27"))
                        .routineEntity(routineEntity)
                        .userEntity(user)
                        .difficulty(Difficulty.MEDIUM)
                        .build(),
                AchievementEntity.builder()
                        .name("Meditation 1hour")
                        .descriptionHowItWasSucceeded("i was calm and relax , one with the floe")
                        .startDate(LocalDate.parse("2021-11-01"))
                        .endDate(LocalDate.parse("2021-11-23"))
                        .routineEntity(routineEntity2)
                        .userEntity(user2)
                        .difficulty(Difficulty.EASY)
                        .build(),
                AchievementEntity.builder()
                        .name("Six Pack")
                        .descriptionHowItWasSucceeded("never skip core day")
                        .startDate(LocalDate.parse("2023-01-27"))
                        .endDate(LocalDate.parse("2023-12-27"))
                        .routineEntity(routineEntity3)
                        .userEntity(user3)
                        .difficulty(Difficulty.HARD)
                        .build()
        ));
    }


    @Test
    void saveAll_checkIfSuccessfullyConvertEntity_Successful() {
        achievementService.saveAll(achievementEntityBeforeCreatinList);

        verify(achievementRepository).saveAll(listArgumentCaptor.capture());

        List<AchievementEntity> result = listArgumentCaptor.getValue();

        assertEquals(achievementEntityList, result);
    }
    @Test
    void getById_withValidId_Successful() throws AchievementNotFoundException {
        Long VALID_ID = 1L;
        when(achievementRepository.findById(VALID_ID)).thenReturn(Optional.of(achievementEntityList.get(0)));

        AchievementEntity result = achievementService.getById(VALID_ID);

        assertEquals(result.getId(), VALID_ID);
    }
    @Test
    void getById_withInvalidId_AchievementNotFoundException() {
        Long INVALID_ID = 1L;
        when(achievementRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        ;

        assertThrows(AchievementNotFoundException.class,
                () -> achievementService.getById(INVALID_ID));
    }
    @Test
    void save_checkIfSuccessfullyConvertEntity_Successful() {

        achievementService.save(achievementEntityBeforeCreatinList.get(0));

        verify(achievementRepository).save(argumentCaptor.capture());

        AchievementEntity result = argumentCaptor.getValue();

        assertEquals(achievementEntityList.get(0), result);
    }
    @Test
    void getAllAchievementsSortedByTimeTook_testSorting_Successful() {
        List<AchievementView> expected = achievementEntityList.
                stream()
                .map(AchievementView::new)
                .sorted((a1, a2) -> a2.getDayTook().compareTo(a1.getDayTook()))
                .toList();

        when(achievementRepository.findAllByUserEntity_Id(SEARCH_ID)).thenReturn(achievementEntityList);

        List<AchievementView> result = achievementService.getAllAchievementsSortedByDifficulty(SEARCH_ID);

        assertEquals(expected, result);
    }
    @Test
    void getAllAchievementsSortedByDifficulty_testSorting_Successful() {
        List<AchievementView> expected = achievementEntityList.
                stream()
                .map(AchievementView::new)
                .sorted((a1, a2) -> a2.getDifficulty().compareTo(a1.getDifficulty()))
                .toList();

        when(achievementRepository.findAllByUserEntity_Id(SEARCH_ID)).thenReturn(achievementEntityList);

        List<AchievementView> result = achievementService.getAllAchievementsSortedByDifficulty(SEARCH_ID);

        assertEquals(expected, result);
    }
    @Test
    void saveWithTwoArguments_validArguments_Successful() throws UserNotFoundException, RoutineNotFoundException {
        AchievementEntity expected = achievementEntityList.get(0);

        AchievementCreate achievementCreate = AchievementCreate.builder()
                .name(expected.getName())
                .descriptionHowItWasSucceeded(expected.getDescriptionHowItWasSucceeded())
                .startDate(expected.getStartDate())
                .endDate(expected.getEndDate())
                .difficulty(expected.getDifficulty())
                .routineId(expected.getRoutineEntity().getId())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(routineRepository.findById(1L)).thenReturn(Optional.of(routineEntity));

        achievementService.save(achievementCreate, 1L);

        verify(achievementRepository, times(1)).save(argumentCaptor.capture());

        AchievementEntity result = argumentCaptor.getValue();

        assertEquals(expected, result);


    }
    @Test
    void saveWithTwoArguments_invalidArguments_Exceptions() {
        AchievementEntity expected = achievementEntityList.get(0);

        AchievementCreate achievementCreate = AchievementCreate.builder()
                .name(expected.getName())
                .descriptionHowItWasSucceeded(expected.getDescriptionHowItWasSucceeded())
                .startDate(expected.getStartDate())
                .endDate(expected.getEndDate())
                .difficulty(expected.getDifficulty())
                .routineId(expected.getRoutineEntity().getId())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(routineRepository.findById(1L)).thenReturn(Optional.of(routineEntity));

        assertThrows(UserNotFoundException.class, () -> achievementService.save(achievementCreate, 1L));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(routineRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class, () -> achievementService.save(achievementCreate, 1L));
    }
    @Test
    void edit_validArguments_Successful() throws RoutineNotFoundException, AchievementNotFoundException {
        AchievementEntity toBeChanged = achievementEntityList.get(0);
        AchievementEntity expected = achievementEntityList.get(1);

        AchievementChange build = AchievementChange.builder()
                .id(toBeChanged.getId())
                .name(expected.getName())
                .descriptionHowItWasSucceeded(expected.getDescriptionHowItWasSucceeded())
                .startDate(expected.getStartDate())
                .endDate(expected.getEndDate())
                .difficulty(expected.getDifficulty())
                .routineId(expected.getRoutineEntity().getId())
                .dayTook(expected.getDayTook())
                .build();

        when(achievementRepository.findById(toBeChanged.getId())).thenReturn(Optional.of(toBeChanged));
        when(routineRepository.findById(build.getRoutineId())).thenReturn(Optional.of(expected.getRoutineEntity()));

        achievementService.edit(build);

        verify(achievementRepository).save(argumentCaptor.capture());

        AchievementEntity result = argumentCaptor.getValue();
        assertEquals(expected , result);
    }
    @Test
    void edit_invalidRouteArgument_ThrowsRoutineNotFoundException(){
        AchievementEntity toBeChanged = achievementEntityList.get(0);
        AchievementEntity expected = achievementEntityList.get(1);

        AchievementChange build = AchievementChange.builder()
                .id(toBeChanged.getId())
                .name(expected.getName())
                .descriptionHowItWasSucceeded(expected.getDescriptionHowItWasSucceeded())
                .startDate(expected.getStartDate())
                .endDate(expected.getEndDate())
                .difficulty(expected.getDifficulty())
                .routineId(expected.getRoutineEntity().getId())
                .dayTook(expected.getDayTook())
                .build();

        when(achievementRepository.findById(toBeChanged.getId())).thenReturn(Optional.of(toBeChanged));
        when(routineRepository.findById(build.getRoutineId())).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class , () -> achievementService.edit(build));
    }
    @Test
    void edit_invalidAchievementArgument_ThrowsAchievementNotFoundException(){
        AchievementEntity toBeChanged = achievementEntityList.get(0);
        AchievementEntity expected = achievementEntityList.get(1);

        AchievementChange build = AchievementChange.builder()
                .id(toBeChanged.getId())
                .name(expected.getName())
                .descriptionHowItWasSucceeded(expected.getDescriptionHowItWasSucceeded())
                .startDate(expected.getStartDate())
                .endDate(expected.getEndDate())
                .difficulty(expected.getDifficulty())
                .routineId(expected.getRoutineEntity().getId())
                .dayTook(expected.getDayTook())
                .build();

        when(achievementRepository.findById(toBeChanged.getId())).thenReturn(Optional.empty());

        assertThrows(AchievementNotFoundException.class , () -> achievementService.edit(build));
    }
    @Test
    void getAllAchievementsView() {
        List<AchievementView> expected = achievementEntityList.
                stream()
                .map(AchievementView::new)
                .toList();
        when(achievementRepository.findAllByUserEntity_Id(SEARCH_ID)).thenReturn(achievementEntityList);

        List<AchievementView> result = achievementService.getAllAchievementsView(SEARCH_ID);

        assertEquals(expected , result);
    }
}