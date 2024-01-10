package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.review.ReactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/reaction")
public class ReactionController {
    private final ReactionService reactionService;

    @GetMapping(value = "/{rno}")
    public int insert(@PathVariable Long rno) {
        log.info("controller : "+rno);
        return reactionService.insert(rno);
    }

    @DeleteMapping("/{rno}")
    public int delete(@PathVariable Long rno) {
        return reactionService.delete(rno);
    }
}
