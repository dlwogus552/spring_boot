package com.fitness.spring_boot.repository.qna.search;

import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.domain.qna.QQNABoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class QNABoardSearchImpl extends QuerydslRepositorySupport implements QNABoardSearch {
    public QNABoardSearchImpl() {
        super(QNABoard.class);
    }

    @Override
    public Page<QNABoard> searchAll(String type, String keyword, Pageable pageable) {
//        QQNABoard
        return null;
    }
}
