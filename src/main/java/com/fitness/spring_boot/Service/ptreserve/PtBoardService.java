package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;

import java.util.Date;
import java.util.List;

public interface PtBoardService {
    List<PtBoardDTO> chechReserve(Date reserve, Long tno);
    void makeReservation(PtBoardDTO ptBoardDTO);
}
