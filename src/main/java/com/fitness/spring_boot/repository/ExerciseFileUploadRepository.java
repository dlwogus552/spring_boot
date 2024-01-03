package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.ExerciseFile;
import com.fitness.spring_boot.dto.ExerciseFileDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseFileUploadRepository extends JpaRepository<ExerciseFile, Long> {
    List<ExerciseFileDTO> findByExercise_EnoOrderByEfno(Long eno);
}
