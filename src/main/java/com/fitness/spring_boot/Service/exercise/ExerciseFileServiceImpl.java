package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;
import com.fitness.spring_boot.repository.exercise.ExerciseFileUploadRepository;
import lombok.RequiredArgsConstructor;
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
public class ExerciseFileServiceImpl implements ExerciseFileService{
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
    public void upload(ExerciseDTO exerciseDTO) {
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
                    .exercise(modelMapper.map(exerciseDTO,Exercise.class))
                    .build();
            exerciseFileUploadRepository.save(exerciseFile);
        }

    }

    @Override
    public void deleteAll(Long eno) {
            List<ExerciseFileDTO> fileDTOList = getViewList(eno);
            //파일 삭제
            for (ExerciseFileDTO dto : fileDTOList) {
                File file = new File(uploadPath, dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
                file.delete();
                if (dto.isThumbnail()) {
                    file = new File(uploadPath, "s_" + dto.getSaveFileName(dto.getFilename(), dto.getUuid()));
                    file.delete();
                }
            }
        exerciseFileUploadRepository.deleteExerciseFileByExercise_Eno(eno);
    }


}
