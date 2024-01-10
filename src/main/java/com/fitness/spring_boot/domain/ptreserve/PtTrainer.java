package com.fitness.spring_boot.domain.ptreserve;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pt_trainer")
public class PtTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno; // 강사 번호
    private String tname; // 강사 이름
    private String tphone; // 강사 휴대폰 번호
}
