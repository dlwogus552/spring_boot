package com.fitness.spring_boot.repository.review.Custom;

import com.fitness.spring_boot.domain.review.QReviewReply;
import com.fitness.spring_boot.domain.review.ReviewReply;
import com.fitness.spring_boot.dto.review.QReviewReplyResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewReplyCustomRepositoryImpl implements ReviewReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    QReviewReply reviewReply = QReviewReply.reviewReply;

    @Override
    public List<ReviewReplyResponseDTO> findReviewReplyByRno(Long rno) {
        return jpaQueryFactory.select(new QReviewReplyResponseDTO(reviewReply))
                .from(reviewReply)
                .where(reviewReply.review.rno.eq(rno))
                .orderBy(reviewReply.root.asc(),reviewReply.leftNum.asc())
                .fetch();
    }

    @Override
    public void updateLeftRight(ReviewReply newreviewReply) {
        jpaQueryFactory.update(reviewReply)
                .set(reviewReply.leftNum, reviewReply.leftNum.add(2))
                .where(reviewReply.root.eq(newreviewReply.getRoot())
                        .and(reviewReply.leftNum.gt(newreviewReply.getRightNum())))
                .execute();

        jpaQueryFactory.update(reviewReply)
                .set(reviewReply.rightNum, reviewReply.rightNum.add(2))
                .where(reviewReply.root.eq(newreviewReply.getRoot())
                        .and(reviewReply.leftNum.gt(newreviewReply.getRightNum())))
                .execute();
    }
}
