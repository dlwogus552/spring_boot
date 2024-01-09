package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.repository.exercise.ExerciseFileUploadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExerciseFileServiceImpl implements ExerciseFileService {
    private final ExerciseFileUploadRepository exerciseFileUploadRepository;
    private final ModelMapper modelMapper;
    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseFileDTO> getList() {
        List<ExerciseFile> result = exerciseFileUploadRepository.findAll();
        List<ExerciseFileDTO> list = result.stream().map(files -> modelMapper.map(files, ExerciseFileDTO.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<ExerciseFileDTO> getViewList(Long eno) {
        List<ExerciseFile> result = exerciseFileUploadRepository.findByExercise_EnoOrderByEfno(eno);
        List<ExerciseFileDTO> list = result.stream().map(files -> modelMapper.map(files, ExerciseFileDTO.class)).collect(Collectors.toList());
        return list;
    }

    @Override
    @Transactional
    public void upload(ExerciseDTO exerciseDTO) {
        log.info("upload"+exerciseDTO);
        //upload파일 생성
        File folder = new File(uploadPath + "\\exercise");
        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //파일 업로드 & 썸네일 업로드
        int i = 0;
        for (MultipartFile file : exerciseDTO.getFiles()) {
            String originalFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            Path savePath = Paths.get(folder.getPath(), uuid + "_" + originalFileName);
            boolean image = false;
            boolean thumbnail = false;
            try {
                file.transferTo(savePath);
                if (i == 0) {
                    thumbnail = true;
                    i++;
                    if (Files.probeContentType(savePath).startsWith("image")) {
                        image = true;
                        File thumbFile = new File(folder.getPath(), "s_" + uuid + "_" + originalFileName);
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);
                    }
                }else if(Files.probeContentType(savePath).startsWith("image")){
                    image=true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ExerciseFile exerciseFile = ExerciseFile.builder()
                    .uuid(uuid)
                    .filename(originalFileName)
                    .image(image)
                    .thumbnail(thumbnail)
                    .exercise(modelMapper.map(exerciseDTO, Exercise.class))
                    .build();
            exerciseFileUploadRepository.save(exerciseFile);
        }//for end
    }

    @Override
    public void deleteAll(Long eno) {
        List<ExerciseFileDTO> fileDTOList = getViewList(eno);
        //파일 삭제
        for (ExerciseFileDTO dto : fileDTOList) {
            File file = new File(uploadPath + "\\exercise", dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
            file.delete();
            if (dto.isThumbnail()) {
                file = new File(uploadPath + "\\exercise", "s_" + dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
                file.delete();
            }
        }
        exerciseFileUploadRepository.deleteExerciseFileByExercise_Eno(eno);
    }
}
