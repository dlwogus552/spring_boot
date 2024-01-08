package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import org.springframework.stereotype.Service;

@Service
public class PtBoardServiceImpl implements PtBoardService {

    public void makeReservation(PtBoardDTO ptBoardDTO) {
        // 여기에 예약 로직을 구현합니다.
        // 리포지토리나 외부 시스템과 상호 작용할 수 있습니다.
        // 현재는 간단히 예약 정보를 출력하는 것으로 대체합니다.
        System.out.println("예약 정보: " + ptBoardDTO);
    }
}
