package com.fitness.spring_boot.repository.foodInfo;

import com.fitness.spring_boot.domain.FoodTable;

import java.util.List;

public interface FoodInfo {
    List<FoodTable> search();
}
