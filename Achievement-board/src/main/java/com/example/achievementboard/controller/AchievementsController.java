package com.example.achievementboard.controller;

import com.example.achievementboard.constants.dtos.AchievementCreate;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.achievement.AchievementService;
import com.example.achievementboard.service.routine.RoutineService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/achievement")
public class AchievementsController extends BaseController{
    private final AchievementService achievementService;
    private final RoutineService routineService;
    @GetMapping
    public ModelAndView getAchievementPage(HttpSession session, ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allAchievements" , achievementService.getAllAchievements(user));
        return setView("allAchievements" , modelAndView);
    }

    @GetMapping("/sortByTimeTook")
    public ModelAndView getAchievementPageSortedByTime(HttpSession session, ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allAchievements" , achievementService.getAllAchievementsSortedByTimeTook(user));
        return setView("allAchievements" , modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getAchievementPageSortedByDiff(HttpSession session, ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allAchievements" , achievementService.getAllAchievementsSortedByDifficulty(user));
        return setView("allAchievements" , modelAndView);
    }
    @GetMapping("/create")
    public ModelAndView getCretePage(@ModelAttribute ("achievementCreate")AchievementCreate achievementCreate,
                                     HttpSession session,
                                     ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }
        User user = getUser(session);

        modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user));
        return setView("achievementCreate" , modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView createAchievement(@Valid @ModelAttribute ("achievementCreate")AchievementCreate achievementCreate,
                                          BindingResult result,
                                          HttpSession session,
                                          ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        if(result.hasErrors()){
            modelAndView.setViewName("achievementCreate");
            return modelAndView;
        }
        User user = getUser(session);
        achievementService.save(achievementCreate , user);
        return redirect("/achievement" , modelAndView);
    }
}
