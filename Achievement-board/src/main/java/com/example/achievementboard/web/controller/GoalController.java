package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.constants.DataChecker;
import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.dtos.goal.GoalCreate;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.service.goal.GoalService;
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
@RequestMapping("/goal")
public class GoalController extends BaseController{

    private final GoalService goalService;
    private final RoutineService routineService;

    @GetMapping
    public ModelAndView getGoalPage(HttpSession session , ModelAndView modelAndView){
        UserView user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalViews(user.getId()));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByEndDate")
    public ModelAndView getGoalsSortedByEndDate(HttpSession session , ModelAndView modelAndView){
        UserView user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalViews(user.getId()));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByImportance")
    public ModelAndView getGoalsSortByImportance(HttpSession session , ModelAndView modelAndView){
        UserView user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByImportance(user.getId()));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getGoalsSortByDifficulty(HttpSession session , ModelAndView modelAndView){
        UserView user = getUser(session);

        modelAndView.addObject("allGoals" , goalService.getAllGoalsSortByDifficulty(user.getId()));
        return setView("allGoals" , modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView getCreateGoalPage(@ModelAttribute(name = "goalCreate")GoalCreate goalCreate,
                                   ModelAndView modelAndView,
                                   HttpSession session){
        UserView user = getUser(session);
        modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user.getId()));

        return setView("goalCreate.html" , modelAndView);
    }
    @PostMapping("/create")
    public ModelAndView createGoal(@Valid @ModelAttribute(name = "goalCreate")GoalCreate goalCreate,
                                   BindingResult result,
                                   ModelAndView modelAndView,
                                   HttpSession session){
        UserView user = getUser(session);
        DataChecker.check(result ,goalCreate.getStartDate() , goalCreate.getEndDate() ,"goalCreate" , "startDate");

        if(result.hasErrors()){
            modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user.getId()));
            modelAndView.setViewName("goalCreate");
            return modelAndView;
        }

        goalService.save(goalCreate , user.getId());

        return redirect("/goal" , modelAndView);
    }
    @GetMapping("/detail/{id}")
    public ModelAndView getCreateGoalPage(@PathVariable(name = "id") String id,
                                          ModelAndView modelAndView,
                                          HttpSession session){
        UserView user = getUser(session);
        GoalEntity goalEntity = goalService.getById(Long.parseLong(id));

        modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user.getId()));
        modelAndView.addObject("currentGoal" , goalEntity.toGoalView());

        return setView("goalDetail.html" , modelAndView);
    }
    @PostMapping("/edit")
    public ModelAndView getCreateGoalPage(@Valid @ModelAttribute(name = "currentGoal") GoalChange goalChange,
                                          BindingResult result,
                                          ModelAndView modelAndView,
                                          HttpSession session){
        UserView user = getUser(session);
        DataChecker.check(result , goalChange.getBeginDate() , goalChange.getEndDate() ,"currentGoal" , "beginDate");

        if(result.hasErrors()){
            modelAndView.addObject("allRoutines" , routineService.getAllRoutines(user.getId()));
            modelAndView.setViewName("goalDetail");
            return modelAndView;
        }

        goalService.edit(goalChange);

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
