package com.fitness.spring_boot.dto.qna;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QNABoardDTO {
    private Long qnabno;                    // 번호
    @NotEmpty
    private String title;                   // 제목
    @NotEmpty
    private String content;                 // 내용
    @NotEmpty
    private String writer;                  // 작성자
    private LocalDateTime writedate;        // 작성일
    private Long readcnt;                   // 읽은 수
    private boolean answer;                 // 답변 여부
    public List<MultipartFile> files;
}
