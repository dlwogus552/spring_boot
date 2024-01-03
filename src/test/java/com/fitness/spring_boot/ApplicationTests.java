package com.fitness.spring_boot;

import com.fitness.spring_boot.Service.exercise.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
@RequiredArgsConstructor
class ApplicationTests {
    private final ExerciseService exerciseService;

}
