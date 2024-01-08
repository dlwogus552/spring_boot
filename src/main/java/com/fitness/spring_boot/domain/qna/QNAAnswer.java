package com.fitness.spring_boot.domain.qna;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "qna_board_answer")
public class QNAAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaano;                    // 번호
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qnabno")
    private QNABoard qnaBoard;

}
