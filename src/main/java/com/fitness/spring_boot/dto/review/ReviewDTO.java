package com.fitness.spring_boot.dto.review;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long rno;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String writer;
    private int bestCount;
    private int westCount;
    private int visitCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    List<MultipartFile> files;
}
