package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReviewReplyService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyRequestDTO;
import com.fitness.spring_boot.dto.review.ReviewReplyResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/replies")
public class ReviewReplyController {
    private final ReviewReplyService reviewReplyService;
    @GetMapping("/list/{rno}")
    public PageResponseDTO<ReviewReplyResponseDTO> getList(@PathVariable Long rno, PageRequestDTO pageRequestDTO){
//    public List<ReviewReplyResponseDTO> getList(@PathVariable Long rno, PageRequestDTO pageRequestDTO){
        PageResponseDTO<ReviewReplyResponseDTO> responseDTO = reviewReplyService.findAll(rno, pageRequestDTO);
//        List<ReviewReplyResponseDTO> responseDTO = reviewReplyService.getList(rno);
        log.info(responseDTO.getDtoList());
        log.info(responseDTO);
        return responseDTO;
    }
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@Valid @RequestBody ReviewReplyRequestDTO reviewReplyRequestDTO,
                                     BindingResult bindingResult) throws BindException {
        log.info(reviewReplyRequestDTO);
        if(bindingResult.hasErrors()){
            return "내용을 입력하세요";
        }
        reviewReplyService.create(reviewReplyRequestDTO);
        return "입력되었습니다.";
    }

    @DeleteMapping("/{rrno}")
    public String remove(@PathVariable("rrno") Long rrno){
        log.info("remove controller");
        reviewReplyService.delete(rrno);
        return "삭제되었습니다.";
    }

}
