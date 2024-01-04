package com.fitness.spring_boot.repository.exercise;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.repository.exercise.search.ExerciseSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, ExerciseSearch {
}
