package com.fitness.spring_boot.repository.review;

import com.fitness.spring_boot.domain.review.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {
}
