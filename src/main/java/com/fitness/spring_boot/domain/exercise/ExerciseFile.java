package com.fitness.spring_boot.domain.exercise;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "exercise_file")
public class ExerciseFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long efno;
    private String uuid;
    private String filename;
    private boolean image;
    private boolean thumbnail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="eno")
    private Exercise exercise;
}
