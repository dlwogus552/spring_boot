package com.fitness.spring_boot.repository.qna.search;

import com.fitness.spring_boot.domain.qna.QNABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QNABoardSearch {
    Page<QNABoard> searchAll(String type, String keyword, Pageable pageable);
    Page<QNABoard> searchAllNoAnswer(String type, String keyword, Pageable pageable);
}
