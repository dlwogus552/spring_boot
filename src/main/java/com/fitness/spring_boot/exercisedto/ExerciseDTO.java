package com.fitness.spring_boot.exercisedto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long eno;
    private String title;
    private String content;
    private String part;
    private LocalDateTime regDate;
    private int visitcount;
    private List<MultipartFile> files;




}
