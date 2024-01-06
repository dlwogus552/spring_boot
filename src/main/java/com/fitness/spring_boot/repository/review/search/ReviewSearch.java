package com.fitness.spring_boot.repository.review.search;

import com.fitness.spring_boot.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewSearch {
    Page<Review> searchAll(String[] types, String keyword, Pageable pageable);

}
