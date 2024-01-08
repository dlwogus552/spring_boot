package com.fitness.spring_boot.dto.review;

import com.fitness.spring_boot.domain.review.ReviewReply;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewReplyResponseDTO {
    private Long id;
    private String content;
    private String writer;
    private Long rno;
    private Long parentId;
    private LocalDateTime regDate;
    private List<ReviewReply> children;

    @QueryProjection
    public ReviewReplyResponseDTO(ReviewReply ReviewReply){
        this.id=ReviewReply.getRrno();
        this.content = ReviewReply.getContent();
        this.writer = ReviewReply.getWriter();
        this.rno = ReviewReply.getReview().getRno();
        if(ReviewReply.getParent() != null){
            this.parentId = ReviewReply.getParent().getRrno();
        }
        this.regDate = ReviewReply.getRegDate();

    }

}
