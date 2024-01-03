package com.fitness.spring_boot.domain.qna;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "qna_board")
public class QNABoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnabno;                    // 번호
    @Column(nullable = false)
    private String title;                   // 제목
    @Lob
    @Column(nullable = false)
    private String content;                 // 내용
    @Column(nullable = false)
    private String writer;                  // 작성자
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime writedate;        // 작성일
    private Long readcnt;                   // 읽은 수
    // 답글을 위한 변수
    private Long root;
    private Long step = 0L;
    private Long indent = 0L;
}
