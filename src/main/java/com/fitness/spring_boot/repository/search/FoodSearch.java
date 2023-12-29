package com.fitness.spring_boot.repository.search;

import com.fitness.spring_boot.domain.FoodTable;

import java.util.List;

public interface FoodSearch {
    List<FoodTable> foodSearch();
}
