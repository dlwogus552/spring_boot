package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.FoodInfoServiceImpl;
import com.fitness.spring_boot.dto.FoodInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/foodInfo")
@RequiredArgsConstructor
@Log4j2
public class foodInfoController {
    private final FoodInfoServiceImpl service;

    @GetMapping("/info")
    public void searchList(String keyword, Model model) {
        List<FoodInfoDTO> dtoList = service.search(keyword);
        log.info(dtoList);
        model.addAttribute("list", dtoList);
    }

}
