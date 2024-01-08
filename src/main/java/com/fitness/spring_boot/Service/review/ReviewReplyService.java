package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyRequestDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyResponseDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ReviewReplyService {
    void create(ReviewReplyRequestDTO reviewReplyRequestDTO);
    void delete(Long rrno);
    PageResponseDTO<ReviewReplyResponseDTO> findAll(Long rno, PageRequestDTO pageRequestDTO);
}
