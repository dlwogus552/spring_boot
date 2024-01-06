package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReviewFileService;
import com.fitness.spring_boot.Service.review.ReviewService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
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
    public void reviewModify(PageRequestDTO PageRequestDTO, Long rno, Model model) {
        model.addAttribute("reviewDTO", reviewService.getBoard(rno));
        model.addAttribute("fileDTOList", reviewFileService.getList(rno));
    }

    @GetMapping("/register")
    public void reviewRegister() {
    }
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void reviewRegisterPro(ReviewDTO reviewDTO){
        reviewService.register(reviewDTO);
    }
    @Transactional
    @PostMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void modify(PageRequestDTO pageRequestDTO, ReviewDTO reviewDTO) {
        reviewService.modify(reviewDTO);
        log.info(reviewDTO);
        if (reviewDTO.getFiles() != null && !reviewDTO.getFiles().get(0).isEmpty()) {
            reviewFileService.deleteAll(reviewDTO.getRno());
            reviewFileService.upload(reviewDTO);
        }
    }

    @GetMapping("/remove")
    @Transactional
    public String remove(Long rno) {
        reviewFileService.deleteAll(rno);
        reviewService.remove(rno);
        return "redirect:/review/list";
    }


}