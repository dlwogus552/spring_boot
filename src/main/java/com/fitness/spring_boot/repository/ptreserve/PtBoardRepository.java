package com.fitness.spring_boot.repository.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PtBoardRepository extends JpaRepository<PtBoard, Long> {
    @Query("select pt from PtBoard pt where pt.trainer.tno =:tno and pt.reserve=:reserve")

    List<PtBoard> findByReserveAndTrainer(Date reserve, Long tno);
}
