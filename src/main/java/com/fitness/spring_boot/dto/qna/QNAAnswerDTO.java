package com.fitness.spring_boot.dto.qna;

import com.fitness.spring_boot.domain.qna.QNABoard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QNAAnswerDTO {
    private Long qnaano;                    // 번호
    private String title;                   // 제목
    private String writer;                  // 작성자
    private String content;                 // 내용
    private LocalDateTime writedate;        // 작성일
    private QNABoard qnaBoard;
}
