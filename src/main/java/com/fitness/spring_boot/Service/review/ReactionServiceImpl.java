package com.fitness.spring_boot.Service.review;

import com.fitness.spring_boot.Service.member.MemberService;
import com.fitness.spring_boot.domain.review.ReviewReaction;
import com.fitness.spring_boot.domain.review.Review;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import com.fitness.spring_boot.repository.review.ReactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ReactionRepository reactionRepository;
    private final MemberService memberService;
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    public int insert(Long rno) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ReviewDTO reviewDTO = reviewService.getBoard(rno);
        Member member = memberService.findBymId(username);
        log.info(member);
        if (reactionRepository.findByMember_MnoAndReview_Rno(member.getMno(), reviewDTO.getRno()) == null) {
            ReviewReaction reviewReaction = ReviewReaction.builder()
                    .member(member)
                    .review(modelMapper.map(reviewDTO, Review.class))
                    .build();
            reactionRepository.save(reviewReaction);
            int count = reviewService.updateCount(reviewDTO, true);
            return count;
        }
        return 0;
    }

    public int delete(Long rno) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ReviewDTO reviewDTO = reviewService.getBoard(rno);
        Member member = memberService.findBymId(username);
        log.info(member);
        ReviewReaction reviewReaction = reactionRepository.findByMember_MnoAndReview_Rno(member.getMno(), rno);

        if (reviewReaction != null) {
            reactionRepository.delete(reviewReaction);
            return reviewService.updateCount(reviewDTO, false);
        }
        return 0;
    }

    @Override
    public boolean getBest(Long rno) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberService.findBymId(username);
        if (member != null) {
            ReviewReaction reviewReaction = reactionRepository.findByMember_MnoAndReview_Rno(member.getMno(), rno);
            log.info(reviewReaction != null);
            if (reviewReaction != null) {
                return true;
            }
        }
        return false;
    }

}
