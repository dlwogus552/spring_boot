package com.fitness.spring_boot.repository.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PtBoardRepository extends JpaRepository<PtBoard, Long> {
    // 추가적인 쿼리 메서드가 필요하다면 여기에 추가 가능
}
