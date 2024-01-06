package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.exercise.ExerciseDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;

public interface ReviewService {
    void register(ReviewDTO reviewDTO);
    PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO);
    Long modify(ReviewDTO reviewDTO);
    void remove(Long rno);
    ReviewDTO getBoard(Long rno);
}
