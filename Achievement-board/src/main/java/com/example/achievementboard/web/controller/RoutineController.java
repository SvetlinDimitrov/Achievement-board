package com.example.achievementboard.web.controller;

import com.example.achievementboard.domain.constants.exception.AchievementNotFoundException;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.dtos.routine.RoutineCreate;
import com.example.achievementboard.domain.dtos.routine.RoutineChange;
import com.example.achievementboard.domain.dtos.user.UserView;
import com.example.achievementboard.service.routine.RoutineService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routine")
@AllArgsConstructor
public class RoutineController extends BaseController {
    private final RoutineService service;

    @GetMapping
    public ModelAndView getRoutinePage(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutines(user.getId()));
        return setView("allRoutines", modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getRoutinePageSortedByDifficulty(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutinesSortedByDifficulty(user.getId()));
        return setView("allRoutines", modelAndView);
    }

    @GetMapping("/sortByHourSpend")
    public ModelAndView getRoutinePageSortedByHourSpend(HttpSession session, ModelAndView modelAndView) {
        UserView user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutinesSortByHourSpend(user.getId()));
        return setView("allRoutines", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView getCreatePage(ModelAndView modelAndView) {
        modelAndView.addObject("createRoutine", new RoutineCreate());
        return setView("routineCreate", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView createGoal(@Valid @ModelAttribute("createRoutine") RoutineCreate createRoutine,
                                   BindingResult result,
                                   ModelAndView modelAndView,
                                   HttpSession session) throws UserNotFoundException {
        UserView user = getUser(session);

        if (result.hasErrors()) {
            modelAndView.setViewName("routineCreate");
            return modelAndView;
        }

        service.add(createRoutine, user.getId());
        return redirect("/routine", modelAndView);
    }

    @GetMapping("/detail/{id}")
    public ModelAndView getDetailPage(@PathVariable(name = "id") String id,
                                      ModelAndView modelAndView) throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException, AchievementNotFoundException {

        modelAndView.addObject("viewRoutine", service.getById(Long.parseLong(id)).toRoutineView());
        return setView("routineDetail", modelAndView);
    }
    @PostMapping("/edit")
    public ModelAndView editGoal(@Valid @ModelAttribute("viewRoutine") RoutineChange viewRoutine,
                                   BindingResult result,
                                   ModelAndView modelAndView) throws RoutineNotFoundException {

        if (result.hasErrors()) {
            modelAndView.setViewName("routineDetail");
            return modelAndView;
        }

        service.edit(viewRoutine);
        return redirect("/routine", modelAndView);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteRoutine(@PathVariable(name = "id") String id,
                                      ModelAndView modelAndView) {

        service.deleteRoutine(Long.parseLong(id));
        return redirect("/routine", modelAndView);
    }
}
