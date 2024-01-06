package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.dto.review.ReviewDTO;
import com.fitness.spring_boot.dto.review.ReviewFileDTO;

import java.util.List;

public interface ReviewFileService {
    List<ReviewFileDTO> getList(Long rno);
    void upload(ReviewDTO reviewDTO);
    void deleteAll(Long rno);

}
