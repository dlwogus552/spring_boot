package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.domain.review.Review;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import com.fitness.spring_boot.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ReviewFileService reviewFileService;
    @Override
    public void register(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        Long rno = reviewRepository.save(review).getRno();
        if(reviewDTO.getFiles() !=null && !reviewDTO.getFiles().get(0).isEmpty()) {
            reviewDTO.setRno(rno);
            reviewFileService.upload(reviewDTO);
        }
    }

    @Override
    public PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("rno");
        Page<Review> result = reviewRepository.searchAll(type, keyword, pageable);

        List<ReviewDTO> dtoList = result.getContent().stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ReviewDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public Long modify(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getRno()).orElseThrow();
        review.change(reviewDTO.getTitle(), reviewDTO.getContent());
        return reviewRepository.save(review).getRno();
    }

    @Override
    public void remove(Long rno) {
        reviewRepository.deleteById(rno);
    }

    @Override
    public ReviewDTO getBoard(Long rno) {
        Review result = reviewRepository.findById(rno).get();
        result.updateVisitCount();
        reviewRepository.save(result);
        ReviewDTO reviewDTO = modelMapper.map(result, ReviewDTO.class);
        return reviewDTO;
    }
}
