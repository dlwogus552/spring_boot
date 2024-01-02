package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.domain.FoodTable;
import com.fitness.spring_boot.dto.FoodInfoDTO;
import com.fitness.spring_boot.repository.FoodTableRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodInfoServiceImpl implements FoodInfoService{
    private final ModelMapper modelMapper;
    private final FoodTableRepository repository;

    @Override
    public List<FoodInfoDTO> search(String keyword) {
        List<FoodTable> result = repository.findFoodTableByFnameContaining(keyword);
        List<FoodInfoDTO> list = result.stream()
                .map(foodInfo->modelMapper.map(foodInfo, FoodInfoDTO.class))
                .toList();
        return list;
    }

    @Override
    public List<FoodInfoDTO> foodSelect(ArrayList<String> fnoList) {
        List<FoodTable> result = repository.findFoodTableByFnoIn(fnoList);
        List<FoodInfoDTO> list = result.stream()
                .map(foodInfo -> modelMapper.map(foodInfo, FoodInfoDTO.class))
                .toList();
        return list;
    }
}
