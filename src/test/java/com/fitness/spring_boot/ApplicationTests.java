package com.fitness.spring_boot;


import com.fitness.spring_boot.Service.exercise.ExerciseService;
import com.fitness.spring_boot.Service.exercise.ExerciseService;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.repository.exercise.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
@RequiredArgsConstructor
class ApplicationTests {
    private final ExerciseService exerciseService;

}
