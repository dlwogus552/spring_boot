package com.fitness.spring_boot.repository.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PtBoardRepository extends JpaRepository<PtBoard, Long> {
    @Query("select pt from PtBoard pt where pt.trainer.tno =:tno and pt.reserve=:reserve")
    List<PtBoard> findByReserveAndTrainer(Date reserve, Long tno);


    @Transactional
    @Modifying
    @Query("delete from PtBoard pb where pb.trainer.tno=:tno and pb.member.mId=:mno and pb.reserve=:reserve")
    void deleteReserve(@Param("tno") Long tno, @Param("mno") Long mno, @Param("reserve") Date reserve);
}
