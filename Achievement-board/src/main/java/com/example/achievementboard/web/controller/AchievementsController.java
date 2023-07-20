package com.example.achievementboard.web.controller;

import com.example.achievementboard.constants.DataChecker;
import com.example.achievementboard.constants.dtos.achievement.AchievementCreate;
import com.example.achievementboard.constants.dtos.achievement.AchievementView;
import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.User;
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
        User user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievements(user));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/sortByTimeTook")
    public ModelAndView getAchievementPageSortedByTime(HttpSession session, ModelAndView modelAndView) {
        User user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievementsSortedByTimeTook(user));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getAchievementPageSortedByDiff(HttpSession session, ModelAndView modelAndView) {
        User user = getUser(session);

        modelAndView.addObject("allAchievements", achievementService.getAllAchievementsSortedByDifficulty(user));
        return setView("allAchievements", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView getCretePage(@ModelAttribute("achievementCreate") AchievementCreate achievementCreate,
                                     HttpSession session,
                                     ModelAndView modelAndView) {
        User user = getUser(session);

        modelAndView.addObject("allRoutines", routineService.getAllRoutines(user));
        return setView("achievementCreate", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView createAchievement(@Valid @ModelAttribute("achievementCreate") AchievementCreate achievementCreate,
                                          BindingResult result,
                                          HttpSession session,
                                          ModelAndView modelAndView) {
        User user = getUser(session);
        DataChecker.check(result, achievementCreate.getStartDate(), achievementCreate.getEndDate(), "achievementCreate", "startDate");

        if (result.hasErrors()) {
            modelAndView.addObject("allRoutines", routineService.getAllRoutines(user));
            modelAndView.setViewName("achievementCreate");
            return modelAndView;
        }

        achievementService.save(achievementCreate, user);
        return redirect("/achievement", modelAndView);
    }

    @GetMapping("/detail/{id}")
    public ModelAndView getCretePage(@PathVariable(name = "id") String id,
                                     HttpSession session,
                                     ModelAndView modelAndView) {
        Achievement achievement = achievementService.getById(Long.parseLong(id));

        User user = getUser(session);
        modelAndView.addObject("allRoutines", routineService.getAllRoutines(user));
        modelAndView.addObject("achievementCreate", achievement.toDto());
        return setView("achievementDetail", modelAndView);
    }

    @PostMapping("/detailEdit")
    public ModelAndView editAch(@Valid @ModelAttribute("achievementCreate") AchievementView achievementView,
                                BindingResult result,
                                ModelAndView modelAndView,
                                HttpSession session) {
        User user = getUser(session);
        DataChecker.check(result, achievementView.getStartDate(), achievementView.getEndDate(), "achievementCreate", "startDate");

        if (result.hasErrors()) {
            modelAndView.addObject("allRoutines", routineService.getAllRoutines(user));
            modelAndView.setViewName("achievementDetail");
            return modelAndView;
        }

        achievementService.edit(achievementView);
        return redirect("/achievement", modelAndView);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteAch(@PathVariable(name = "id") String id) {
        achievementService.deleteAch(Long.parseLong(id));

        return redirect("/achievement", new ModelAndView());
    }
}
