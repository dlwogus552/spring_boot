package com.fitness.spring_boot.Service.exercise;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseFileDTO;

import java.util.List;

public interface ExerciseFileService {
    List<ExerciseFileDTO> getList();
    List<ExerciseFileDTO> getViewList(Long eno);
    void upload(ExerciseDTO exerciseDTO);
    void deleteAll(Long eno);

}
