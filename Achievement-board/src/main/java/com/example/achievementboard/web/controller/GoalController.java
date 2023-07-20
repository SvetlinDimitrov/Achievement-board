package com.example.achievementboard.web.controller;

import com.example.achievementboard.constants.DataChecker;
import com.example.achievementboard.constants.dtos.achievement.AchievementView;
import com.example.achievementboard.constants.dtos.goal.GoalCreate;
import com.example.achievementboard.constants.dtos.goal.GoalView;
import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.goal.GoalService;
import com.example.achievementboard.service.routine.RoutineService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
@RequestMapping("/goal")
public class GoalController extends BaseController{
    private final GoalService goalService;
    private final RoutineService routineService;
    @GetMapping
    public ModelAndView getGoalPage(HttpSession session , ModelAndView modelAndView){
        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoals(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByEndDate")
    public ModelAndView getGoalsSortedByEndDate(HttpSession session , ModelAndView modelAndView){
        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortedByDate(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByImportance")
    public ModelAndView getGoalsSortByImportance(HttpSession session , ModelAndView modelAndView){
        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByImportance(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getGoalsSortByDifficulty(HttpSession session , ModelAndView modelAndView){
        User user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByDifficulty(user));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView getCreateGoalPage(@ModelAttribute(name = "goalCreate")GoalCreate goalCreate,
                                   ModelAndView modelAndView,
                                   HttpSession session){
        User user = getUser(session);
        modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user));

        return setView("goalCreate.html" , modelAndView);
    }
    @PostMapping("/create")
    public ModelAndView createGoal(@Valid @ModelAttribute(name = "goalCreate")GoalCreate goalCreate,
                                   BindingResult result,
                                   ModelAndView modelAndView,
                                   HttpSession session){
        User user = getUser(session);
        DataChecker.check(result ,goalCreate.getStartDate() , goalCreate.getEndDate() ,"goalCreate" , "startDate");

        if(result.hasErrors()){
            modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user));
            modelAndView.setViewName("goalCreate");
            return modelAndView;
        }

        goalService.save(goalCreate , user);

        return redirect("/goal" , modelAndView);
    }
    @GetMapping("/detail/{id}")
    public ModelAndView getCreateGoalPage(@PathVariable(name = "id") String id,
                                          ModelAndView modelAndView,
                                          HttpSession session){
        User user = getUser(session);
        Goal goal = goalService.getById(Long.parseLong(id));
        modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user));
        modelAndView.addObject("currentGoal" , goal.toGoalView());

        return setView("goalDetail.html" , modelAndView);
    }
    @PostMapping("/edit")
    public ModelAndView getCreateGoalPage(@Valid @ModelAttribute(name = "currentGoal") GoalView goalView,
                                          BindingResult result,
                                          ModelAndView modelAndView,
                                          HttpSession session){
        User user = getUser(session);
        DataChecker.check(result ,goalView.getBeginDate() , goalView.getEndDate() ,"currentGoal" , "beginDate");

        if(result.hasErrors()){
            modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user));
            modelAndView.setViewName("goalDetail");
            return modelAndView;
        }

        goalService.edit(goalView);

        return redirect("/goal" , modelAndView);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGoal(@PathVariable(name = "id") String id){
        goalService.deleteGoal(Long.parseLong(id));
        return redirect("/goal" , new ModelAndView());
    }

    @GetMapping("/finish/{id}")
    public ModelAndView finishGoal(@PathVariable(name = "id") String id){
        goalService.finishGoal(Long.parseLong(id));
        return redirect("/achievement" , new ModelAndView());
    }

}
