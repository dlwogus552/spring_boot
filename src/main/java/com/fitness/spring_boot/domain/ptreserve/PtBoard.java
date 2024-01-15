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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "mno")
    private Member member; // 회원이름
    private Date reserve; // 예약 날짜
    private String timeSlot; // 예약한 시간

    @ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "tno")
    private PtTrainer trainer;
}