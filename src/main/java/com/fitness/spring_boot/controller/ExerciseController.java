package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.ExerciseFileService;
import com.fitness.spring_boot.Service.ExerciseService;
import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.exercisedto.ExerciseDTO;
import com.fitness.spring_boot.exercisedto.ExerciseFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseFileService exerciseFileService;
    private final ExerciseService exerciseService;
    private String uploadPath="";
    @GetMapping("/list")
    public void exercise(){}
    @GetMapping({"/view","/modify"})
    public void view(Long bno, Model model){}
    @GetMapping("/register")
    public void register(){}
    @GetMapping("/remove")
    public String remove(Long bno){
        return "redirect:/exercise/list";
    }

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(ExerciseDTO exerciseDTO, ExerciseFileDTO uploadFileDTO, Model model){
        // 글 작성
        exerciseService.register(exerciseDTO);



        //파일 업로드
        if(uploadFileDTO.getFiles()!=null){
            List<ExerciseFileDTO> list = new ArrayList<>();

            for (MultipartFile file : uploadFileDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
                log.info(originalFileName);
//                uploadPath=uploadPath+"\\"+getFolder();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                boolean video = false;

                try {
                    file.transferTo(savePath);
                    if (Files.probeContentType(savePath).startsWith("video")) {
                        video = true;
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ExerciseFile exerciseFile = ExerciseFile.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .video(video)
                        .build();
                Long fno = exerciseFileService.upload(exerciseFile);

                list.add(ExerciseFileDTO.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .video(video)
                        .build());
            }
            model.addAttribute("fileList",list);
        }
    }

}
