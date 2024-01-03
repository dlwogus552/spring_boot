package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.foodInfo.FoodInfoServiceImpl;
import com.fitness.spring_boot.dto.foodInfo.FoodInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value="/foodSelect", method={RequestMethod.POST})
    @ResponseBody
    public ResponseEntity<List<FoodInfoDTO>> foodSelect(@RequestParam(value="fnoList[]") ArrayList<String> fnoList) {
        log.info("여기 들어옴????");
        log.info("fnoList : "+fnoList);
        List<FoodInfoDTO> list = service.foodSelect(fnoList);
        log.info(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
