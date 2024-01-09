package com.fitness.spring_boot.Service.review;


public interface ReactionService {
    int insert(Long rno);
    int delete(Long rno);
    boolean getBest(Long rno);
}
