package com.example.achievementboard.controller;

import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.goal.GoalService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/goal")
public class GoalController extends BaseController{
    private final GoalService goalService;
    @GetMapping
    public ModelAndView getGoalPage(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoals(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByEndDate")
    public ModelAndView getGoalsSortedByEndDate(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortedByDate(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByImportance")
    public ModelAndView getGoalsSortByImportance(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByImportance(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getGoalsSortByDifficulty(HttpSession session , ModelAndView modelAndView){
        if(session.getAttribute("user") == null){
            return redirect("/login" , new ModelAndView());
        }

        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByDifficulty(user));
        return setView("allGoals" , modelAndView);
    }
}
