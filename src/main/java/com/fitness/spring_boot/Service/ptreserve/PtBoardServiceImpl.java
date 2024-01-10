package com.fitness.spring_boot.Service.ptreserve;

import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.dto.review.ReviewDTO;
import com.fitness.spring_boot.repository.ptreserve.PtBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class PtBoardServiceImpl implements PtBoardService {
    private final PtBoardRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<PtBoardDTO> chechReserve(Date reserve, Long tno) {
        log.info("repository 전 "+reserve);
        List<PtBoard> boardList = repository.findByReserveAndTrainer(reserve, tno);
        log.info("repository 후 "+boardList);
        if(boardList!=null && boardList.get(0)!=null) {
//            List<PtBoardDTO> ptBoardDTOList = modelMapper.map(boardList,PtBoardDTO.class);
            List<PtBoardDTO> ptBoardDTOList = boardList.stream().map(result-> modelMapper.map(result,PtBoardDTO.class)).collect(Collectors.toList());
            return ptBoardDTOList;
        }
        return null;
    }

    public void makeReservation(PtBoardDTO ptBoardDTO) {
        log.info("ptBoardDTO" + ptBoardDTO);
        PtBoard ptBoard = modelMapper.map(ptBoardDTO, PtBoard.class);
        repository.save(ptBoard);
    }
}
