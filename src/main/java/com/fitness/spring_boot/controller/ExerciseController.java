package com.fitness.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {
    @GetMapping("/list")
    public void exercise(){}
    @GetMapping({"/view","/modify"})
    public void view(Long bno, Model model){}
    @GetMapping("/register")
    public void register(){}
    @GetMapping("/remove")
    public String remove(Long bno){
        return "redirect:/exercise/list";
    }

}
