package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.domain.FoodTable;
import com.fitness.spring_boot.dto.FoodInfoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class FoodTableTest {
    @Autowired
    private FoodTableRepository repository;

    @Test
    public void select1() {
        Long count = repository.count();
        log.info("count : " + count);// 수정중
    }
    @Test
    public void searchAll() {
//        ArrayList<String> fnoList= {"1", "2", "3"};
//        List<FoodTable> result = repository.findFoodTableByFnoIn(fnoList);
//        log.info(result);
    }
}
