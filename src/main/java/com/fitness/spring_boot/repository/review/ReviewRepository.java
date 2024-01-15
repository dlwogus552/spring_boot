package com.fitness.spring_boot.repository.review;


import com.fitness.spring_boot.domain.review.Review;
import com.fitness.spring_boot.repository.review.search.ReviewSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewSearch {

}
