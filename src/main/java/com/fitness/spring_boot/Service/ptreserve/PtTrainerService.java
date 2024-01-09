package com.fitness.spring_boot.Service.ptreserve;


import com.fitness.spring_boot.domain.ptreserve.PtTrainer;

public interface PtTrainerService {
    void initPtTrainer();

    PtTrainer getTrainer(Long tno);
}
