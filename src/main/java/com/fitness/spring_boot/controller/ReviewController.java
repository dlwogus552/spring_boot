package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReviewFileService;
import com.fitness.spring_boot.Service.review.ReviewService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping({"/view","/modify"})
    public void reviewModify() {
    }

    @GetMapping("/register")
    public void reviewRegister() {
    }
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void reviewRegisterPro(ReviewDTO reviewDTO){
        reviewService.register(reviewDTO);
    }



}