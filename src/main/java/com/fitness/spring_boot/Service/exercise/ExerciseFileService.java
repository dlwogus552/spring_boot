package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;

import java.util.List;

public interface ExerciseFileService {
    List<ExerciseFileDTO> getList();
    List<ExerciseFileDTO> getViewList(Long eno);
    ExerciseFileDTO getFile(Long efno);
    void delete(Long fno);
    Long upload(ExerciseFile file);
    void deleteAll(Long eno);
}
