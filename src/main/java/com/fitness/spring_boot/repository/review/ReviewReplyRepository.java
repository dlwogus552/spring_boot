package com.fitness.spring_boot.repository.review;

import com.fitness.spring_boot.domain.review.ReviewReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {
    @Query("select r from ReviewReply r where r.review.rno=:rno")
    Page<ReviewReply> listOfReview(Long rno, Pageable pageable);
}
