package com.fitness.spring_boot.dto.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.entity.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PtBoardDTO {
    private Long pno;
    private Member member; // 회원이름
    private Date reserve; // 예약 날짜
    private String timeSlot; // 예약한 시간
    private PtTrainer trainer;
}
