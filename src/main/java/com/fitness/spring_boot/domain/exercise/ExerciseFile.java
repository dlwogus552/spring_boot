package com.fitness.spring_boot.domain.exercise;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
// 엔티티에 setter 를 모두 여는건 좋은 방법이 아님 @Setter 참고로 필드에도 선언 가능하디
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
