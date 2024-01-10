package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReactionService;
import com.fitness.spring_boot.Service.review.ReviewFileService;
import com.fitness.spring_boot.Service.review.ReviewService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {
    private final ReviewFileService reviewFileService;
    private final ReviewService reviewService;
    private final ReactionService reactionService;
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;
    @GetMapping("/list")
    public void reviewlist(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ReviewDTO> responseDTO = reviewService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping({"/view","/modify"})
    public void reviewModify(PageRequestDTO PageRequestDTO, Long rno, Model model) {
        model.addAttribute("best",reactionService.getBest(rno));
        log.info(reactionService.getBest(rno));
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
    @GetMapping(value = "/download/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent
            , @PathVariable("filename") String filename) {
        log.info("download file: " + filename);
        Resource resource = new FileSystemResource(uploadPath+"\\review\\"+filename);
        if (resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("resource: " + resource);
        String resourceName = resource.getFilename();
        String resourceOriginalName = resourceName.substring(resourceName.lastIndexOf("_") + 1);
        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadName = null;
            // 한글 파일이름 처리
            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8")
                        .replaceAll("\\+", " ");
            } else if (userAgent.contains("Edge")) {
                log.info("Edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            } else {
                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }
            headers.add("Content-Disposition", "attachment; filename=" + downloadName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

}