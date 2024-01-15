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
        List<PtBoard> boardList = repository.findByReserveAndTrainer(reserve, tno);
        if(boardList!=null && boardList.get(0)!=null) {
//            List<PtBoardDTO> ptBoardDTOList = modelMapper.map(boardList,PtBoardDTO.class);
            List<PtBoardDTO> ptBoardDTOList = boardList.stream().map(result-> modelMapper.map(result,PtBoardDTO.class)).collect(Collectors.toList());
            return ptBoardDTOList;
        }
        return null;
    }

    public void makeReservation(PtBoardDTO ptBoardDTO) {
        PtBoard ptBoard = modelMapper.map(ptBoardDTO, PtBoard.class);
        repository.save(ptBoard);
    }

    @Override
    public void remove(Date reserve, Long tno, Long mno) {
        log.info("remove확인"+reserve);
        log.info("remove확인"+tno);
        log.info("remove확인"+mno);

        repository.deleteReserve(tno,mno,reserve);
    }

//    public void remove(Long tno,Long mno,Date reserve) {
//        log.info("remove확인"+reserve);
//        log.info("remove확인"+tno);
//        log.info("remove확인"+mno);
//
//        repository.deleteReserve(tno,mno,reserve);
//    }

    @Override
    public void removeById(Long pno) {
        repository.deleteById(pno);
    }
}
