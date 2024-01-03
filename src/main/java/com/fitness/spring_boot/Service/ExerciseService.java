package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.dto.ExerciseDTO;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;

public interface ExerciseService {
    Long register(ExerciseDTO exerciseDTO);
    PageResponseDTO<ExerciseDTO> getList(PageRequestDTO pageRequestDTO);
    Long modify(ExerciseDTO exerciseDTO);
    void remove(Long eno);
    ExerciseDTO getBoard(Long eno);
}
