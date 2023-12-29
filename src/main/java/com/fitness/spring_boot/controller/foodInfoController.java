package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.FoodInfoServiceImpl;
import com.fitness.spring_boot.dto.FoodInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/foodInfo")
@RequiredArgsConstructor
@Log4j2
public class foodInfoController {
    private final FoodInfoServiceImpl service;

    @GetMapping("/info")
    public void view() {

    }

    @GetMapping("/info/{keyword}")
    @ResponseBody
    public ResponseEntity<List<FoodInfoDTO>> searchList(@PathVariable("keyword") String keyword, Model model) {
        List<FoodInfoDTO> dtoList = service.search(keyword);
        log.info(dtoList);
//        model.addAttribute("list", dtoList);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
