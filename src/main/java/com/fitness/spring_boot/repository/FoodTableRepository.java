package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.FoodTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTableRepository extends JpaRepository<FoodTable, String> {

}
