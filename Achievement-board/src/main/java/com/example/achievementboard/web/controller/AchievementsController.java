package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.constants.DataChecker;
import com.example.achievementboard.domain.dtos.achievement.AchievementCreate;
import com.example.achievementboard.domain.dtos.achievement.AchievementChange;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.achievement.AchievementService;
import com.example.achievementboard.service.routine.RoutineService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/achievement")
public class AchievementsController extends BaseController {

    private final AchievementService achievementService;
    private final RoutineService routineService;


    @GetMapping
    public ModelAndView getAchievementPage(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievementsView(user.getId()));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/sortByTimeTook")
    public ModelAndView getAchievementPageSortedByTime(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievementsSortedByTimeTook(user.getId()));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getAchievementPageSortedByDiff(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievementsSortedByDifficulty(user.getId()));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView getCretePage(@ModelAttribute("achievementCreate") AchievementCreate achievementCreate,
                                     HttpSession session,
                                     ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allRoutines" ,routineService.getAllRoutines(user.getId()));
        return setView("achievementCreate", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView createAchievement(@Valid @ModelAttribute("achievementCreate") AchievementCreate achievementCreate,
                                          BindingResult result,
                                          HttpSession session,
                                          ModelAndView modelAndView) {
        UserView user = getUser(session);
        DataChecker.check(result, achievementCreate.getStartDate(), achievementCreate.getEndDate(), "achievementCreate", "startDate");

        if (result.hasErrors()) {
            modelAndView.addObject("allRoutines", routineService.getAllRoutines(user.getId()));
            modelAndView.setViewName("achievementCreate");
            return modelAndView;
        }

        achievementService.save(achievementCreate, user.getId());
        return redirect("/achievement", modelAndView);
    }

    @GetMapping("/detail/{id}")
    public ModelAndView getCretePage(@PathVariable(name = "id") String id,
                                     HttpSession session,
                                     ModelAndView modelAndView) {

        AchievementEntity achievementEntity = achievementService.getById(Long.parseLong(id));

        UserView user = getUser(session);

        modelAndView.addObject("allRoutines", routineService.getAllRoutines(user.getId()));
        modelAndView.addObject("achievementCreate", achievementEntity.toDto());
        return setView("achievementDetail", modelAndView);
    }

    @PostMapping("/detailEdit")
    public ModelAndView editAch(@Valid @ModelAttribute("achievementCreate") AchievementChange achievementChange,
                                BindingResult result,
                                ModelAndView modelAndView,
                                HttpSession session) {
        UserView user = getUser(session);
        DataChecker.check(result, achievementChange.getStartDate(), achievementChange.getEndDate(), "achievementCreate", "startDate");

        if (result.hasErrors()) {
            modelAndView.addObject("allRoutines", routineService.getAllRoutines(user.getId()));
            modelAndView.setViewName("achievementDetail");
            return modelAndView;
        }

        achievementService.edit(achievementChange);
        return redirect("/achievement", modelAndView);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteAch(@PathVariable(name = "id") String id) {
        achievementService.deleteAch(Long.parseLong(id));

        return redirect("/achievement", new ModelAndView());
    }
}
