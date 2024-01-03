package com.fitness.spring_boot.repository.exercise.search;

import com.fitness.spring_boot.domain.exercise.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExerciseSearch {
    Page<Exercise> searchAll(String type, String keyword, Pageable pageable);
}
