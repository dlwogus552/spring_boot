package com.fitness.spring_boot.dto.ptreserve;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PtBoardDTO {
    private Long tno; // 강사 번호
    private Long mno; // 회원 번호
    private String time; // 예약한 시간
    private String reserve; // 예약 날짜
}
