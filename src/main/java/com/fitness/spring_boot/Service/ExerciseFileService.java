package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.exercisedto.ExerciseFileDTO;

import java.util.List;

public interface ExerciseFileService {
    List<ExerciseFileDTO> getList(Long bno);
    ExerciseFileDTO getFile(Long fno);
    void delete(Long fno);
    Long upload(ExerciseFile file);
    void deleteAll(Long bno);
}
