package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.FoodTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodTableRepository extends JpaRepository<FoodTable, String> {
    List<FoodTable> findFoodTableByFnameContaining(String keyword);
}
