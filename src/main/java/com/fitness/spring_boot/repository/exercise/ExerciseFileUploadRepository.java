package com.fitness.spring_boot.repository.exercise;

import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseFileUploadRepository extends JpaRepository<ExerciseFile, Long> {
    List<ExerciseFile> findByExercise_EnoOrderByEfno(Long eno);
    void deleteExerciseFileByExercise_Eno(Long eno);
}
