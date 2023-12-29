package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.Exercise;
import com.fitness.spring_boot.repository.search.ExerciseSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, ExerciseSearch {
}
