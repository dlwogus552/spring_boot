package com.fitness.spring_boot.repository.foodInfo;

import com.fitness.spring_boot.domain.foodInfo.FoodTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FoodTableRepository extends JpaRepository<FoodTable, String> {
    List<FoodTable> findFoodTableByFnameContaining(String keyword);

    List<FoodTable> findFoodTableByFnoIn(ArrayList<String> fnoList);
}
