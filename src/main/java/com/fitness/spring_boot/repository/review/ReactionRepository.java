package com.fitness.spring_boot.repository.review;

import com.fitness.spring_boot.domain.review.ReviewReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<ReviewReaction, Long> {
    ReviewReaction findByMember_MnoAndReview_Rno(Long mno, Long rno);
}
