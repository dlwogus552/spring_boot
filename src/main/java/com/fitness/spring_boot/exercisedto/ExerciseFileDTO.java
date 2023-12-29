package com.fitness.spring_boot.exercisedto;

import com.fitness.spring_boot.domain.Exercise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseFileDTO {
    private Long fno;
    private String uuid;
    private String filename;
    private boolean video;
    private Exercise exercise;
    private List<MultipartFile> files;
}
