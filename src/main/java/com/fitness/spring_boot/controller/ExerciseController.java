package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.exercise.ExerciseFileService;
import com.fitness.spring_boot.Service.exercise.ExerciseService;
import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void list(PageRequestDTO pageRequestDTO, Model model, ExerciseFileDTO exerciseFileDTO) {
        PageResponseDTO<ExerciseDTO> responseDTO=exerciseService.getList(pageRequestDTO);
        List<ExerciseFileDTO> fileDTOList = exerciseFileService.getList();
        model.addAttribute("responseDTO",responseDTO);
        model.addAttribute("fileDTOList",fileDTOList);
    }
    @GetMapping({"/view","/modify"})
    public void view(PageRequestDTO PageRequestDTO, Long eno, Model model){
        model.addAttribute("exerciseDTO",exerciseService.getBoard(eno));
        model.addAttribute("fileDTOList",exerciseFileService.getViewList(eno));
    }
    @GetMapping("/register")
    public void register(String errorMsg, ExerciseDTO exerciseDTO){}
    @GetMapping("/remove")
    @Transactional
    public String remove(Long eno){
        List<ExerciseFileDTO> fileDTOList = exerciseFileService.getViewList(eno);

        for(ExerciseFileDTO dto : fileDTOList) {
            File file = new File(uploadPath, dto.getSaveFileName(dto.getFilename(),dto.getUuid()));
            file.delete();
            if(dto.isThumbnail()){
                file = new File(uploadPath, "s_"+dto.getSaveFileName(dto.getFilename(),dto.getUuid()));
                file.delete();
            }
        }
        exerciseFileService.deleteAll(eno);
        exerciseService.remove(eno);
        return "redirect:/exercise/list";
    }
    @PostMapping(value = "/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@Valid ExerciseDTO exerciseDTO, Model model){
        if(exerciseDTO.getFiles()!=null && !exerciseDTO.getFiles().get(0).isEmpty()){
        // 글 작성
            Long eno = exerciseService.register(exerciseDTO);
        //파일 업로드
            int i = 0;
            for (MultipartFile file : exerciseDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
//                uploadPath=uploadPath+"\\"+getFolder();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                boolean image = false;
                boolean thumbnail = false;
                    try {
                        file.transferTo(savePath);
                        if(i==0) {
                            thumbnail = true;
                            i++;
                            if (Files.probeContentType(savePath).startsWith("image")) {
                                image = true;
                                File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                                Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
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

            }    //for end
            return "redirect:/exercise/list";
        }        //if end
        model.addAttribute("errorMsg","파일을 첨부하세요");
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
    @Transactional
    @PostMapping(value = "/modify",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String modify(PageRequestDTO pageRequestDTO, ExerciseDTO exerciseDTO){

            Long eno = exerciseService.modify(exerciseDTO);
        if(exerciseDTO.getFiles()!=null && !exerciseDTO.getFiles().get(0).isEmpty()) {
            List<ExerciseFileDTO> fileDTOList = exerciseFileService.getViewList(eno);
            //파일 삭제
            for (ExerciseFileDTO dto : fileDTOList) {
                File file = new File(uploadPath, dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
                file.delete();
                if (dto.isThumbnail()) {
                    file = new File(uploadPath, "s_" + dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
                    file.delete();
                }
            }
            exerciseFileService.deleteAll(eno);
            //파일 업로드
            int i = 0;

            for (MultipartFile file : exerciseDTO.getFiles()) {
                String originalFileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" + originalFileName);
                boolean image = false;
                boolean thumbnail = false;
                try {
                    file.transferTo(savePath);
                    if (i == 0) {
                        thumbnail = true;
                        i++;
                        if (Files.probeContentType(savePath).startsWith("image")) {
                            image = true;
                            File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalFileName);
                            Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
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
        }

            }
        return "redirect:/exercise/view?eno="+eno+"&"+pageRequestDTO.getLink();
    }

}
