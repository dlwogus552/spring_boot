package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.ExerciseFileService;
import com.fitness.spring_boot.Service.ExerciseService;
import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.dto.ExerciseDTO;
import com.fitness.spring_boot.dto.ExerciseFileDTO;
import com.fitness.spring_boot.dto.ExercisePageRequestDTO;
import com.fitness.spring_boot.dto.ExercisePageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;
    private final ModelMapper modelMapper;

    @GetMapping("/list")
    public void list(ExercisePageRequestDTO exercisePageRequestDTO, Model model, ExerciseFileDTO exerciseFileDTO) {
        ExercisePageResponseDTO<ExerciseDTO> responseDTO=exerciseService.getList(exercisePageRequestDTO);
        List<ExerciseFileDTO> fileDTOList = exerciseFileService.getList();
        log.info(responseDTO);
        log.info("file"+fileDTOList);
        model.addAttribute("responseDTO",responseDTO);
        model.addAttribute("fileDTOList",fileDTOList);


    }
    @GetMapping({"/view","/modify"})
    public void view(ExercisePageRequestDTO exercisePageRequestDTO, Long eno, Model model){
        model.addAttribute("exerciseDTO",exerciseService.getBoard(eno));
        model.addAttribute("fileList",exerciseFileService.getViewList(eno));
    }
    @GetMapping("/register")
    public void register(){}
    @GetMapping("/remove")
    public String remove(Long bno){
        return "redirect:/exercise/list";
    }

    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(ExerciseDTO exerciseDTO, Model model){
        if(exerciseDTO.getFiles()!=null){
        // 글 작성
            log.info(exerciseDTO);
            Long eno = exerciseService.register(exerciseDTO);
        //파일 업로드
            int i = 0;
            List<ExerciseFileDTO> list = new ArrayList<>();

            for (MultipartFile file : exerciseDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
                log.info(originalFileName);
//                uploadPath=uploadPath+"\\"+getFolder();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                boolean image = false;
                boolean thumbnail = false;
                    try {
                        file.transferTo(savePath);
                        if (Files.probeContentType(savePath).startsWith("image")) {
                            image = true;
                            if(i==0) {
                                File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                                Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                                thumbnail = true;
                                i++;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                ExerciseFile exerciseFile = ExerciseFile.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .image(image)
                        .thumbnail(thumbnail)
                        .exercise(modelMapper.map(exerciseService.getBoard(eno), Exercise.class))
                        .build();
                Long fno = exerciseFileService.upload(exerciseFile);

                list.add(ExerciseFileDTO.builder()
                        .uuid(uuid)
                        .filename(originalFileName)
                        .image(image)
                        .thumbnail(thumbnail)
                        .exercise(modelMapper.map(exerciseService.getBoard(eno), Exercise.class))
                        .build());
            }
            model.addAttribute("fileList",list);
            return "redirect:/exercise/list";
        }
        return "/exercise/register";
    }

    @GetMapping("/display/{fileName}")
    public ResponseEntity<Resource> display(@PathVariable("fileName") String fileName){
        Resource resource = new FileSystemResource(uploadPath+File.separator+fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();
        if(fileName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resource);
        }
        try{
            headers.add("Content-Type",Files.probeContentType(resource.getFile().toPath()));
        }catch (Exception e){e.printStackTrace();}
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
