package com.fitness.spring_boot.domain.ptreserve;

import com.fitness.spring_boot.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "pt_board")
public class PtBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private int tno; // 강사 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno")
    private Member member; // 회원이름
    private Date time; // 예약한 시간
    private Date reserve; // 예약 날짜
    private String timeSlot;

    @ManyToOne
    @JoinColumn(name = "tno", insertable = false, updatable = false)
    private PtTrainer trainer;
}