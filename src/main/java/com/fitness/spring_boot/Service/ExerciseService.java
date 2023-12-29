package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.dto.ExerciseDTO;
import com.fitness.spring_boot.dto.ExercisePageRequestDTO;
import com.fitness.spring_boot.dto.ExercisePageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseService {
    Long register(ExerciseDTO exerciseDTO);
    ExercisePageResponseDTO<ExerciseDTO> getList(ExercisePageRequestDTO exercisePageRequestDTO);
    Long modify(ExerciseDTO exerciseDTO);
    void remove(Long eno);
    ExerciseDTO getBoard(Long eno);
}
