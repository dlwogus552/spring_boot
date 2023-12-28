package com.fitness.spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Table(name = "food_table")
public class FoodTable {
    @Id
    private Long fno;               // 식품코드
    @Column(name = "food_name")
    private String fname;               // 식품명
    private Long ncsa;                // 영양성분함량기준량
    private Long kcal;                   // 칼로리
    private double protein;             // 단백질
    private double province;            // 지방
    private double carbohydrate;        // 탄수화물
}
