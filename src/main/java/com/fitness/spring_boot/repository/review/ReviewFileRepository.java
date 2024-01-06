package com.fitness.spring_boot.repository.review;

import com.fitness.spring_boot.domain.exercise.ExerciseFile;
import com.fitness.spring_boot.domain.review.ReviewFile;
import com.fitness.spring_boot.dto.review.ReviewFileDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewFileRepository extends JpaRepository<ReviewFile, Long> {
    List<ReviewFile> findByReview_RnoOrderByRfno(Long rno);
    void deleteReviewFileByReview_Rno(Long rno);
}
