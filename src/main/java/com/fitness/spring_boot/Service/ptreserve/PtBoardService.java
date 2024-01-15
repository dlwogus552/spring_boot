package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.repository.ptreserve.PtBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PtBoardService {
    List<PtBoardDTO> chechReserve(Date reserve, Long tno);
    void makeReservation(PtBoardDTO ptBoardDTO);
    void remove(Date reserve, Long tno,Long mno);

    void removeById(Long pno);
}
