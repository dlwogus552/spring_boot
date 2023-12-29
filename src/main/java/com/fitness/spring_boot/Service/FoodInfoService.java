package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.dto.FoodInfoDTO;

import java.util.List;

public interface FoodInfoService {
    List<FoodInfoDTO> search(String keyword);
}
