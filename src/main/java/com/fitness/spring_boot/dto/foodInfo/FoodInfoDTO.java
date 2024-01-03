package com.fitness.spring_boot.dto.foodInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodInfoDTO {
    private Long fno;               // 식품코드
    private String fname;               // 식품명
    private Long ncsa;                // 영양성분함량기준량
    private Long kcal;                   // 칼로리
    private double protein;             // 단백질
    private double province;            // 지방
    private double carbohydrate;        // 탄수화물
}
