package com.fitness.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/2")
    public String home2() {
        return "index2";
    }
    @GetMapping("/copy")
    public String test(){
        return "copy";
    }
}
