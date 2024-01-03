package com.fitness.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pttime")
public class PttimeController {
    @GetMapping("/trainer")
    public void trainer(){}

    @GetMapping("/calendar")
    public void calendar(){}
}
