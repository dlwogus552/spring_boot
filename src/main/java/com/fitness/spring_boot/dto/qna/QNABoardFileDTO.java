package com.fitness.spring_boot.dto.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QNABoardFileDTO {
    private Long qnafno;
    private String uuid;
    private String filename;
    private QNABoard qnaBoard;
}
