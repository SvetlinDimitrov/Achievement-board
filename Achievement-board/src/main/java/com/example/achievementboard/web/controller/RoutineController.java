package com.example.achievementboard.web.controller;

import com.example.achievementboard.constants.dtos.routine.RoutineCreate;
import com.example.achievementboard.constants.dtos.routine.RoutineView;
import com.example.achievementboard.entity.User;
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
        User user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutines(user));
        return setView("allRoutines", modelAndView);
    }

    @GetMapping("/sortByDifficulty")
    public ModelAndView getRoutinePageSortedByDifficulty(HttpSession session, ModelAndView modelAndView) {
        User user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutinesSortedByDifficulty(user));
        return setView("allRoutines", modelAndView);
    }

    @GetMapping("/sortByHourSpend")
    public ModelAndView getRoutinePageSortedByHourSpend(HttpSession session, ModelAndView modelAndView) {
        User user = getUser(session);

        modelAndView.addObject("allRoutines", service.getAllRoutinesSortByHourSpend(user));
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
                                   HttpSession session) {
        User user = getUser(session);

        if (result.hasErrors()) {
            modelAndView.setViewName("routineCreate");
            return modelAndView;
        }

        service.add(createRoutine, user);
        return redirect("/routine", modelAndView);
    }

    @GetMapping("/detail/{id}")
    public ModelAndView getDetailPage(@PathVariable(name = "id") String id,
                                      ModelAndView modelAndView) {

        modelAndView.addObject("viewRoutine", service.getById(Long.parseLong(id)).toRoutineView());
        return setView("routineDetail", modelAndView);
    }
    @PostMapping("/edit")
    public ModelAndView editGoal(@Valid @ModelAttribute("viewRoutine") RoutineView viewRoutine,
                                   BindingResult result,
                                   ModelAndView modelAndView) {

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
