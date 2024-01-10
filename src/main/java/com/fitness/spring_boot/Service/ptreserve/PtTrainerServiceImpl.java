// PtTrainerServiceImpl.java
package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.repository.ptreserve.PtTrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PtTrainerServiceImpl implements PtTrainerService {

    private final PtTrainerRepository ptTrainerRepository;

    @Override
    public void initPtTrainer() {
        List<PtTrainer> ptTrainerList = new ArrayList<>();
        PtTrainer ptTrainer = new PtTrainer();
        for(int i=1; i<=4;i++){
            ptTrainer.setTname("트레이너"+i);
            ptTrainer.setTphone("010-"+i+""+i+""+i+""+i+"-"+i+""+i+""+i+""+i+"");
            ptTrainerList.add(ptTrainer);
        }
        log.info("pt trainer : " + ptTrainerList);

        for(PtTrainer trainer: ptTrainerList){
            ptTrainerRepository.save(trainer);
        }

        //    tno: 1, tname: 트레이너1, tphone: 010-1234-2345
        //    tno: 2, tname: 트레이너2, tphone: 010-2345-3456
        //    tno: 3, tname: 트레이너3, tphone: 010-3456-4567
        //    tno: 4, tname: 트레이너4, tphone: 010-4567-5678
    }

    @Override
    public PtTrainer getTrainer(Long tno) {
        log.info("Long tno : " + tno);
        PtTrainer trainer = ptTrainerRepository.findByTno(tno);
        log.info(trainer);
        return trainer;
    }
}
