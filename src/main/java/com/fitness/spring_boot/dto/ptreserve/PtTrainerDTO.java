package com.fitness.spring_boot.dto.ptreserve;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PtTrainerDTO {
    private Long tno;          // 트레이너 번호
    private String tname;      // 트레이너 이름
    private String tphone;     // 트레이너 휴대폰 번호

    public PtTrainerDTO() {
    }

    // 필드를 초기화하는 생성자
    public PtTrainerDTO(Long tno, String tname, String tphone) {
        this.tno = tno;
        this.tname = tname;
        this.tphone = tphone;
    }
}
