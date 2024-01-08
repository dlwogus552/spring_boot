package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.repository.ptreserve.PtBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class PtBoardServiceImpl implements PtBoardService {
    private final PtBoardRepository repository;
    private final ModelMapper modelMapper;

    public void makeReservation(PtBoardDTO ptBoardDTO) {
        log.info("ptBoardDTO" + ptBoardDTO);
        PtBoard ptBoard = modelMapper.map(ptBoardDTO, PtBoard.class);
        repository.save(ptBoard);
    }
}
