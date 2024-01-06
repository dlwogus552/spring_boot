package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReviewFileService;
import com.fitness.spring_boot.Service.review.ReviewService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewFileService reviewFileService;
    private final ReviewService reviewService;
    @GetMapping("/list")
    public void reviewlist(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ReviewDTO> responseDTO = reviewService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/content")
    public void reviewcontent() {
    }

    @GetMapping("/modify")
    public void reviewmodify() {
    }

    @GetMapping("/write")
    public void reviewwrite() {
    }



}