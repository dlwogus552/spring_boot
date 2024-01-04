package com.fitness.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class testttttttttt {
    @GetMapping({"/contact","classes","blog","about","main","index2"})
    public void go(){}
}
