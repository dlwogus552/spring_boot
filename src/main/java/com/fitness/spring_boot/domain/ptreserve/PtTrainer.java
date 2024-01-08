package com.fitness.spring_boot.domain.ptreserve;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long tno; // 강사 번호
    private String tname; // 강사 이름
    private String tphone; // 강사 휴대폰 번호



}
