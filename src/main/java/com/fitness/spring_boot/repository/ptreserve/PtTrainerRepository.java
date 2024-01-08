package com.fitness.spring_boot.repository.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PtTrainerRepository extends JpaRepository<PtTrainer, Long> {

//    Optional<PtTrainer> findByTno(Long tno);
    @Query("select t from PtTrainer t where t.tno =:tno")
    PtTrainer findByTno(Long tno);
}
