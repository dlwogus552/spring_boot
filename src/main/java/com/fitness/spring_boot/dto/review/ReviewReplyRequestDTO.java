package com.fitness.spring_boot.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReplyRequestDTO {
    private Long rrno;
    @NotBlank
    private String content;
    private String writer;
    private Long rno;
    private Long parentId;

    @Builder
    public ReviewReplyRequestDTO(String content, String writer, Long rno, Long parentId){
        this.parentId=parentId;
        this.rno=rno;
        this.content=content;
        this.writer=writer;
    }
}
