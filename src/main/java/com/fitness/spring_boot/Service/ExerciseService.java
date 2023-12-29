package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.dto.ExerciseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseService {
    Long register(ExerciseDTO exerciseDTO);
    List<ExerciseDTO> getList(Pageable pageable);
    Long modify(ExerciseDTO exerciseDTO);
    void remove(Long eno);
    Exercise getBoard(Long eno);
}
