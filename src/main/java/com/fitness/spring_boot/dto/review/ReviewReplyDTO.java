package com.fitness.spring_boot.dto.review;

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
public class ReviewReplyDTO {
    private Long rrno;
    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;
    private LocalDateTime postDate;
}
