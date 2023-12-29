package com.fitness.spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "exercise_file")
public class ExerciseFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long efno;
    private String uuid;
    private String filename;
    private boolean video;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="eno")
    private Exercise exercise;
}
