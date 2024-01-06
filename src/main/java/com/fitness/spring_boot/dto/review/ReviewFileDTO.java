package com.fitness.spring_boot.dto.review;

import com.fitness.spring_boot.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewFileDTO {
    private Long rfno;
    private String uuid;
    private String filename;
    private Review review;

    public String getLink(){
        return uuid+"_"+filename;
    }

}
