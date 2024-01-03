package com.fitness.spring_boot.domain.qna;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "qna_board_file")
public class QNABoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnafno;
    private String uuid;
    private String filename;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qnabno")
    private QNABoard qnaBoard;
}
