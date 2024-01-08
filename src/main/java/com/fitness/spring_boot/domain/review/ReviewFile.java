package com.fitness.spring_boot.domain.review;

import com.fitness.spring_boot.domain.exercise.Exercise;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rfno;
    private String uuid;
    private String filename;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rno")
    private Review review;
}
