package com.fitness.spring_boot.repository.search;

import com.fitness.spring_boot.domain.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExerciseSearch {
    Page<Exercise> searchAll(String type, String keyword, Pageable pageable);
}
