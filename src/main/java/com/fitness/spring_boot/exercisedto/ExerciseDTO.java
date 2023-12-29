package com.fitness.spring_boot.exercisedto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

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

}
