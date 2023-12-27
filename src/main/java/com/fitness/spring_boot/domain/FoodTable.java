package com.fitness.spring_boot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_table")
@Builder
@ToString
@Getter
public class FoodTable {
    @Id
    @Column(length = 1000)
    private String 식품코드;
    
}
