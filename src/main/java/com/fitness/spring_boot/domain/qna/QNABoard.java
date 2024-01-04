package com.fitness.spring_boot.domain.qna;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@EntityListeners(value = {AuditingEntityListener.class})
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
    @ColumnDefault("0")
    private Long readcnt = 0L;              // 읽은 수
    @ColumnDefault("0")
    private boolean answer = false;         // 답변 여부
}
