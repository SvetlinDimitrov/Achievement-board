package com.example.achievementboard.service.goal;

import com.example.achievementboard.domain.constants.enums.Difficulty;
import com.example.achievementboard.domain.constants.enums.Importance;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.dtos.goal.GoalCreate;
import com.example.achievementboard.domain.dtos.goal.GoalView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.repos.GoalRepository;
import com.example.achievementboard.repos.RoutineRepository;
import com.example.achievementboard.repos.UserRepository;
import com.example.achievementboard.service.achievement.AchievementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoalServiceImplTest {

    @Mock
    private GoalRepository goalRepository;
    @Mock
    private RoutineRepository routineRepository;
    @Mock
    private AchievementService achievementService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GoalServiceImpl goalService;
    @Captor
    private ArgumentCaptor<GoalEntity> argumentCapture;
    @Captor
    private ArgumentCaptor<AchievementEntity> achievementEntityArgumentCaptor;
    @Captor
    private ArgumentCaptor<List<GoalEntity>> listArgumentCaptor;

    private GoalChange goalChange;
    private GoalCreate goalCreate;
    private List<GoalView> goalViews;
    private List<GoalEntity> goalEntities;
    private AchievementEntity achievement;

    private final static Long VALID_ID = 1L;
    private final static Long INVALID_ID = 100L;

    @BeforeEach
    public void setUp() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        UserEntity user3 = new UserEntity();
        user3.setId(3L);

        RoutineEntity routineEntity = new RoutineEntity();
        routineEntity.setId(1L);
        RoutineEntity routineEntity2 = new RoutineEntity();
        routineEntity2.setId(2L);
        RoutineEntity routineEntity3 = new RoutineEntity();
        routineEntity3.setId(3L);


        goalEntities = List.of(
                GoalEntity.builder()
                        .name("Clime Everest")
                        .importance(Importance.HIGH)
                        .descriptionHowToDoIt("i will walk 15k steps per day")
                        .descriptionWhyYouWantToAchieveIt("i want to be climber")
                        .endDate(LocalDate.parse("2026-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(user)
                        .difficulty(Difficulty.HARD)
                        .pictureRes("/pic/hard.jpg")
                        .routineEntity(routineEntity)
                        .build(),
                GoalEntity.builder()
                        .name("Get Jacked")
                        .importance(Importance.LOW)
                        .descriptionHowToDoIt("i will train 5 times a week")
                        .descriptionWhyYouWantToAchieveIt("i want to have a girl")
                        .endDate(LocalDate.parse("2025-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(user2)
                        .difficulty(Difficulty.MEDIUM)
                        .pictureRes("/pic/medium.jpg")
                        .routineEntity(routineEntity2)
                        .build(),
                GoalEntity.builder()
                        .name("Meditation")
                        .importance(Importance.MEDIUM)
                        .descriptionHowToDoIt("I want to reduce my stress")
                        .descriptionWhyYouWantToAchieveIt("i want to become one with the nature")
                        .endDate(LocalDate.parse("2024-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(user3)
                        .difficulty(Difficulty.EASY)
                        .pictureRes("/pic/easy.jpg")
                        .routineEntity(routineEntity3)
                        .build()
        );
        goalEntities.get(0).setId(1L);
        goalEntities.get(1).setId(2L);
        goalEntities.get(2).setId(3L);

        goalViews = List.of(
                GoalView.builder()
                        .id(1L)
                        .name("Clime Everest")
                        .importance(Importance.HIGH)
                        .descriptionHowToDoIt("i will walk 15k steps per day")
                        .descriptionWhyYouWantToAchieveIt("i want to be climber")
                        .endDate(LocalDate.parse("2026-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userId(user.getId())
                        .difficulty(Difficulty.HARD)
                        .pictureRes("/pic/hard.jpg")
                        .routineId(routineEntity.getId())
                        .build(),
                GoalView.builder()
                        .id(2L)
                        .name("Get Jacked")
                        .importance(Importance.LOW)
                        .descriptionHowToDoIt("i will train 5 times a week")
                        .descriptionWhyYouWantToAchieveIt("i want to have a girl")
                        .endDate(LocalDate.parse("2025-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userId(user2.getId())
                        .difficulty(Difficulty.MEDIUM)
                        .pictureRes("/pic/medium.jpg")
                        .routineId(routineEntity2.getId())
                        .build(),
                GoalView.builder()
                        .id(3L)
                        .name("Meditation")
                        .importance(Importance.MEDIUM)
                        .descriptionHowToDoIt("I want to reduce my stress")
                        .descriptionWhyYouWantToAchieveIt("i want to become one with the nature")
                        .endDate(LocalDate.parse("2024-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userId(user3.getId())
                        .difficulty(Difficulty.EASY)
                        .pictureRes("/pic/easy.jpg")
                        .routineId(routineEntity3.getId())
                        .build()
        );
        goalCreate = GoalCreate.builder()
                .name(goalEntities.get(0).getName())
                .descriptionHowItWillBeDone(goalEntities.get(0).getDescriptionHowToDoIt())
                .bonusDescription(goalEntities.get(0).getDescriptionWhyYouWantToAchieveIt())
                .category(goalEntities.get(0).getCategory())
                .startDate(goalEntities.get(0).getBeginDate())
                .endDate(goalEntities.get(0).getEndDate())
                .difficulty(goalEntities.get(0).getDifficulty())
                .importance(goalEntities.get(0).getImportance())
                .routineId(goalEntities.get(0).getRoutineEntity().getId())
                .build();

        goalChange = GoalChange.builder()
                .name(goalEntities.get(0).getName())
                .descriptionHowToDoIt(goalEntities.get(0).getDescriptionHowToDoIt())
                .descriptionWhyYouWantToAchieveIt(goalEntities.get(0).getDescriptionWhyYouWantToAchieveIt())
                .category(goalEntities.get(0).getCategory())
                .beginDate(goalEntities.get(0).getBeginDate())
                .endDate(goalEntities.get(0).getEndDate())
                .difficulty(goalEntities.get(0).getDifficulty())
                .importance(goalEntities.get(0).getImportance())
                .routineId(goalEntities.get(0).getRoutineEntity().getId())
                .build();

        achievement = AchievementEntity.builder()
                .name(goalEntities.get(0).getName())
                .descriptionHowItWasSucceeded(goalEntities.get(0).getDescriptionHowToDoIt())
                .startDate(goalEntities.get(0).getBeginDate())
                .endDate(goalEntities.get(0).getEndDate())
                .difficulty(goalEntities.get(0).getDifficulty())
                .userEntity(goalEntities.get(0).getUserEntity())
                .routineEntity(goalEntities.get(0).getRoutineEntity())
                .build();
    }

    @Test
    void saveAll_validArguments_SuccessfulyPutThePictures() {
        List<GoalEntity> input = List.of(
                GoalEntity.builder()
                        .name("Clime Everest")
                        .importance(Importance.HIGH)
                        .descriptionHowToDoIt("i will walk 15k steps per day")
                        .descriptionWhyYouWantToAchieveIt("i want to be climber")
                        .endDate(LocalDate.parse("2026-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(goalEntities.get(0).getUserEntity())
                        .difficulty(Difficulty.HARD)
                        .routineEntity(goalEntities.get(0).getRoutineEntity())
                        .build(),
                GoalEntity.builder()
                        .name("Get Jacked")
                        .importance(Importance.LOW)
                        .descriptionHowToDoIt("i will train 5 times a week")
                        .descriptionWhyYouWantToAchieveIt("i want to have a girl")
                        .endDate(LocalDate.parse("2025-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(goalEntities.get(1).getUserEntity())
                        .difficulty(Difficulty.MEDIUM)
                        .routineEntity(goalEntities.get(1).getRoutineEntity())
                        .build(),
                GoalEntity.builder()
                        .name("Meditation")
                        .importance(Importance.MEDIUM)
                        .descriptionHowToDoIt("I want to reduce my stress")
                        .descriptionWhyYouWantToAchieveIt("i want to become one with the nature")
                        .endDate(LocalDate.parse("2024-12-27"))
                        .beginDate(LocalDate.now())
                        .category("sport")
                        .userEntity(goalEntities.get(2).getUserEntity())
                        .difficulty(Difficulty.EASY)
                        .routineEntity(goalEntities.get(2).getRoutineEntity())
                        .build()
        );
        goalService.saveAll(input);

        verify(goalRepository).saveAll(listArgumentCaptor.capture());

        List<GoalEntity> result = listArgumentCaptor.getValue();

        assertEquals(goalEntities, result);

    }

    @Test
    void getById_validId_Successful() throws GoalNotFoundException {
        GoalEntity expected = goalEntities.get(0);

        when(goalRepository.findById(VALID_ID)).thenReturn(Optional.ofNullable(expected));

        GoalEntity result = goalService.getById(VALID_ID);

        assertEquals(expected, result);
    }

    @Test
    void getById_invalidId_ThrowsGoalNotFoundException() {
        when(goalRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(GoalNotFoundException.class, () -> goalService.getById(INVALID_ID));
    }

    @Test
    void save_validArguments_SuccessfulyPutThePicture() {
        GoalEntity expected = goalEntities.get(0);
        GoalEntity input = GoalEntity.builder()
                .name("Clime Everest")
                .importance(Importance.HIGH)
                .descriptionHowToDoIt("i will walk 15k steps per day")
                .descriptionWhyYouWantToAchieveIt("i want to be climber")
                .endDate(LocalDate.parse("2026-12-27"))
                .beginDate(LocalDate.now())
                .category("sport")
                .userEntity(goalEntities.get(0).getUserEntity())
                .difficulty(Difficulty.HARD)
                .routineEntity(goalEntities.get(0).getRoutineEntity())
                .build();

        goalService.save(input);

        verify(goalRepository, times(1)).save(argumentCapture.capture());

        GoalEntity result = argumentCapture.getValue();

        assertEquals(expected, result);
    }

    @Test
    void getAllGoalsSortedByDate_testTheSorting_Successful() {
        when(goalRepository.findAllByUserEntity_IdOrderByEndDate(VALID_ID)).thenReturn(goalEntities);

        List<GoalView> expected = goalViews.stream().sorted((g1, g2) -> g2.getEndDate().compareTo(g1.getEndDate())).toList();

        List<GoalView> result = goalService.getAllGoalsSortedByDate(VALID_ID);

        assertEquals(expected, result);
    }

    @Test
    void getAllGoalsSortByImportance_testTheSorting_Successful() {
        when(goalRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(goalEntities);

        List<GoalView> expected = goalViews.stream().sorted((g1, g2) -> g2.getImportance().compareTo(g1.getImportance())).toList();

        List<GoalView> result = goalService.getAllGoalsSortByImportance(VALID_ID);

        assertEquals(expected, result);
    }

    @Test
    void getAllGoalsSortByDifficulty_testSorting_Successful() {
        when(goalRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(goalEntities);

        List<GoalView> expected = goalViews.stream().sorted((g1, g2) -> g2.getDifficulty().compareTo(g1.getDifficulty())).toList();

        List<GoalView> result = goalService.getAllGoalsSortByDifficulty(VALID_ID);

        assertEquals(expected, result);
    }

    @Test
    void testSave_validData_Successful() throws UserNotFoundException, RoutineNotFoundException {
        GoalEntity expected = goalEntities.get(0);

        when(userRepository.findById(expected.getUserEntity().getId())).thenReturn(Optional.ofNullable(expected.getUserEntity()));
        when(routineRepository.findById(goalCreate.getRoutineId())).thenReturn(Optional.of(expected.getRoutineEntity()));

        goalService.save(goalCreate , expected.getUserEntity().getId());

        verify(goalRepository , times(1)).save(argumentCapture.capture());

        GoalEntity result = argumentCapture.getValue();

        assertEquals(expected , result);


    }
    @Test
    void testSave_invalidUserData_ThrowsUserNotFoundException() {
        GoalEntity expected = goalEntities.get(0);

        when(userRepository.findById(expected.getUserEntity().getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class , () ->goalService.save(goalCreate , expected.getUserEntity().getId()));
    }

    @Test
    void testSave_invalidRoutineData_ThrowsRoutineNotFoundException() {
        GoalEntity expected = goalEntities.get(0);

        when(userRepository.findById(expected.getUserEntity().getId())).thenReturn(Optional.ofNullable(expected.getUserEntity()));
        when(routineRepository.findById(goalCreate.getRoutineId())).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class , () -> goalService.save(goalCreate , expected.getUserEntity().getId()));


    }

    @Test
    void edit_validArguments_Successful() throws RoutineNotFoundException, GoalNotFoundException {
        GoalEntity input = goalEntities.get(1);
        GoalEntity expected = goalEntities.get(0);
        goalChange.setId(2L);

        when(goalRepository.findById(goalChange.getId())).thenReturn(Optional.ofNullable(input));
        when(routineRepository.findById(goalChange.getRoutineId())).thenReturn(Optional.ofNullable(expected.getRoutineEntity()));

        goalService.edit(goalChange);

        verify(goalRepository , times(1)).save(argumentCapture.capture());

        GoalEntity result = argumentCapture.getValue();

        assertEquals(expected , result);
    }

    @Test
    void edit_invalidGoalArgument_ThrowsGoalNotFoundException(){
        when(goalRepository.findById(goalChange.getId())).thenReturn(Optional.empty());

        assertThrows(GoalNotFoundException.class , () -> goalService.edit(goalChange));
    }

    @Test
    void edit_invalidRoutineArgument_ThrowsRoutineNotFoundException(){
        GoalEntity input = goalEntities.get(1);

        goalChange.setId(2L);

        when(goalRepository.findById(goalChange.getId())).thenReturn(Optional.ofNullable(input));
        when(routineRepository.findById(goalChange.getRoutineId())).thenReturn(Optional.empty());

        assertThrows(RoutineNotFoundException.class , () ->goalService.edit(goalChange));
    }

    @Test
    void deleteGoal_valid_Successful(){
        goalService.deleteGoal(VALID_ID);

        verify(goalRepository, times(1)).deleteById(VALID_ID);
    }

    @Test
    void finishGoal_validGoal_Successful() throws GoalNotFoundException {
        GoalEntity finishedGoal = goalEntities.get(0);

        when(goalRepository.findById(finishedGoal.getId())).thenReturn(Optional.of(finishedGoal));

        goalService.finishGoal(finishedGoal.getId());

        verify(achievementService).save(achievementEntityArgumentCaptor.capture());

        AchievementEntity result = achievementEntityArgumentCaptor.getValue();

        assertEquals(achievement , result);

    }

    @Test
    void finishGoal_invalidGoal_ThrowsGoalNotFoundException(){
        GoalEntity finishedGoal = goalEntities.get(0);

        when(goalRepository.findById(finishedGoal.getId())).thenReturn(Optional.empty());


        assertThrows(GoalNotFoundException.class , () -> goalService.finishGoal(finishedGoal.getId()));
    }

    @Test
    void getAllGoalViews_testSortingByUserEntityId_Successful() {
        when(goalRepository.findAllByUserEntity_Id(VALID_ID)).thenReturn(goalEntities);

        List<GoalView> expected = goalViews.stream().sorted(Comparator.comparing(GoalView::getUserId)).toList();

        List<GoalView> result = goalService.getAllGoalViews(VALID_ID);

        assertEquals(expected, result);
    }
}