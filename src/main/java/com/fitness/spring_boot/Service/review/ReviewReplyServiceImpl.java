package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.domain.review.Review;
import com.fitness.spring_boot.domain.review.ReviewReply;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyRequestDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyResponseDTO;
import com.fitness.spring_boot.repository.review.Custom.ReviewReplyCustomRepository;
import com.fitness.spring_boot.repository.review.ReviewReplyRepository;
import com.fitness.spring_boot.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewReplyServiceImpl implements ReviewReplyService{
    private final ReviewReplyCustomRepository reviewReplyCustomRepository;
    private final ReviewReplyRepository reviewReplyRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    @Override
    public void create(ReviewReplyRequestDTO reviewReplyRequestDTO) {
        ReviewReply reviewReply = new ReviewReply(reviewReplyRequestDTO);
        log.info(reviewReply);
        Optional<Review> result = reviewRepository.findById(reviewReplyRequestDTO.getRno());
        log.info(result);
        if(result != null){
            Review review = result.get();
            reviewReply.setReview(review);
        }
        if(reviewReplyRequestDTO.getParentId() != null){
            Optional<ReviewReply> parent = reviewReplyRepository.findById(reviewReplyRequestDTO.getParentId());

            if(parent.isPresent()) {
                parent.get().setChild(reviewReply);
                reviewReplyCustomRepository.updateLeftRight(reviewReply);
                reviewReplyRepository.save(reviewReply);
            }
        }else{
            reviewReplyRepository.save(reviewReply);
            reviewReply.setRootId(reviewReply);
        }
    }

    @Override
    public void delete(Long rrno) {
        Optional<ReviewReply> reviewReply = reviewReplyRepository.findById(rrno);
        if(reviewReply.isPresent()){
            reviewReplyRepository.deleteById(rrno);
        }
    }
//    public List<ReviewReplyResponseDTO> getList(Long rno) {
//        return reviewReplyCustomRepository.findReviewReplyByRno(rno);
//    }
    @Override
    public PageResponseDTO<ReviewReplyResponseDTO> findAll(Long rno, PageRequestDTO pageRequestDTO) {
        reviewReplyCustomRepository.findReviewReplyByRno(rno);
        Pageable pageable= PageRequest.of(
                pageRequestDTO.getPage()<=0 ? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(), Sort.by("rrno").ascending());
        Page<ReviewReply> result = reviewReplyRepository.listOfReview(rno, pageable);
        List<ReviewReplyResponseDTO> dtoList = result.getContent().stream().map(reply -> modelMapper.map(reply,ReviewReplyResponseDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ReviewReplyResponseDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
