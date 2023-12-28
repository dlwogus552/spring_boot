package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
