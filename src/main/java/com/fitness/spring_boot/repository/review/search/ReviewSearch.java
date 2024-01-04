package com.fitness.spring_boot.repository.review.search;

import com.fitness.spring_boot.domain.review.ReviewBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewSearch {
    Page<ReviewBoard> searchAll(String[] types, String keyword, Pageable pageable);

}
