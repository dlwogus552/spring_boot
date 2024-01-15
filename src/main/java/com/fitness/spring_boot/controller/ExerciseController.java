package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.exercise.ExerciseFileService;
import com.fitness.spring_boot.Service.exercise.ExerciseService;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseFileService exerciseFileService;
    private final ExerciseService exerciseService;
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<ExerciseDTO> responseDTO = exerciseService.getList(pageRequestDTO);
        List<ExerciseFileDTO> fileDTOList = exerciseFileService.getList();
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("fileDTOList", fileDTOList);
    }

    @GetMapping({"/view", "/modify"})
    public void view(PageRequestDTO PageRequestDTO, Long eno, Model model) {
        model.addAttribute("exerciseDTO", exerciseService.getBoard(eno));
        model.addAttribute("fileDTOList", exerciseFileService.getViewList(eno));
    }

    @GetMapping("/register")
    public void register(String filesError,ExerciseDTO exerciseDTO) {}

    @GetMapping("/remove")
    @Transactional
    public String remove(Long eno) {
        exerciseFileService.deleteAll(eno);
        exerciseService.remove(eno);
        return "redirect:/exercise/list";
    }
    @Transactional
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@Valid ExerciseDTO exerciseDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors() || (exerciseDTO.getFiles() == null || exerciseDTO.getFiles().get(0).isEmpty())) {
            bindingResult.getFieldErrors().forEach(error -> model.addAttribute(error.getField()+"Error", error.getDefaultMessage()));
            bindingResult.getFieldErrors().forEach(objectError -> log.info(objectError.getField()+" error"));
            model.addAttribute("filesError","파일을 첨부해주세요");
            model.addAttribute("exerciseDTO",exerciseDTO);
            return "/exercise/register";
        }
        exerciseService.register(exerciseDTO);
        return "redirect:/exercise/list";
    }

    @GetMapping("/display/{fileName}")
    public ResponseEntity<Resource> display(@PathVariable("fileName") String fileName) {
        Resource resource = new FileSystemResource(uploadPath+"\\exercise" + File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        if (fileName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resource);
        }
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Transactional
    @PostMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String modify(PageRequestDTO pageRequestDTO, @Valid ExerciseDTO exerciseDTO, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> model.addAttribute(error.getField()+"Error", error.getDefaultMessage()));
            model.addAttribute("exerciseDTO",exerciseDTO);
            return "/exercise/modify";
        }
        exerciseService.modify(exerciseDTO);
        if (exerciseDTO.getFiles() != null && !exerciseDTO.getFiles().get(0).isEmpty()) {
            exerciseFileService.deleteAll(exerciseDTO.getEno());
            exerciseFileService.upload(exerciseDTO);
        }
        return "redirect:/exercise/view?eno=" + exerciseDTO.getEno() + "&" + pageRequestDTO.getLink();
    }
}

