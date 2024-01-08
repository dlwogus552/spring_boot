package com.fitness.spring_boot.repository.review.Custom;

import com.fitness.spring_boot.domain.review.ReviewReply;
import com.fitness.spring_boot.dto.review.ReviewReplyResponseDTO;

import java.util.List;

public interface ReviewReplyCustomRepository {
    List<ReviewReplyResponseDTO> findReviewReplyByRno(Long board_id);
    void updateLeftRight(ReviewReply reviewReply);
}
