package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.dto.ExerciseFileDTO;

import java.util.List;

public interface ExerciseFileService {
    List<ExerciseFileDTO> getList();
    List<ExerciseFileDTO> getViewList(Long eno);
    ExerciseFileDTO getFile(Long efno);
    void delete(Long fno);
    Long upload(ExerciseFile file);
    void deleteAll(Long bno);
}
