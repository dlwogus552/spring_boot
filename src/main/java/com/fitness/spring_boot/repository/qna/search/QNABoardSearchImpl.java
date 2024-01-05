package com.fitness.spring_boot.repository.qna.search;

import com.fitness.spring_boot.domain.exercise.Exercise;
import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.domain.qna.QQNABoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class QNABoardSearchImpl extends QuerydslRepositorySupport implements QNABoardSearch {
    public QNABoardSearchImpl() {
        super(QNABoard.class);
    }

    @Override
    public Page<QNABoard> searchAll(String type, String keyword, Pageable pageable) {
        QQNABoard board = QQNABoard.qNABoard;
        JPQLQuery<QNABoard> query = this.from(board);
        if (type != null && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            switch (type) {
                case "title":
                    booleanBuilder.or(board.title.contains(keyword));
                    break;
                case "writer":
                    booleanBuilder.or(board.writer.contains(keyword));
                    break;
            }
            query.where(booleanBuilder);
        }//end if
        query.where(board.qnabno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<QNABoard> list = query.fetch();
        Long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
}
