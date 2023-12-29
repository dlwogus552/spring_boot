package com.fitness.spring_boot.repository.Exercise;

import com.fitness.spring_boot.domain.ExerciseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseFileUploadRepository extends JpaRepository<ExerciseFile, Long> {
}
