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
    @Column(name = "food_code", length = 1000)
    private String fcode;               // 식품코드
    @Column(name = "food_name")
//    @Lob
    private String fname;               // 식품명
    @Column(name = "data_c_code")
//    @Lob
    private String dataCode;            // 데이터 구분코드
    @Column(name = "data_c_name")
//    @Lob
    private String dataName;            // 데이터 구분명
    private int kcal;                   // 칼로리
    private String ncsa;                // 영양성분함량기준량
    private double protein;             // 단백질
    private double province;            // 지방
    private double carbohydrate;        // 탄수화물

}
