package com.fitness.spring_boot.repository.qna;

import com.fitness.spring_boot.domain.qna.QNAAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QNAAnswerRepository extends JpaRepository<QNAAnswer, Long> {
    @Query("select a from QNAAnswer a where a.qnaBoard.qnabno =:qnabno")
    QNAAnswer findByQnabno(Long qnabno);
}
