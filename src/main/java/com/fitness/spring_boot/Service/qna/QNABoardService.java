package com.fitness.spring_boot.Service.qna;

import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;

public interface QNABoardService {
    PageResponseDTO<QNABoardDTO> getList(PageRequestDTO pageRequestDTO);

    Long register(QNABoardDTO qnaBoardDTO);

    Long modify(QNABoardDTO qnaBoardDTO);

    void remove(Long bno);

    QNABoardDTO getBoard(Long bno);

    PageResponseDTO<QNABoardDTO> getNoAnswerList(PageRequestDTO pageRequestDTO);

    PageResponseDTO<QNABoardDTO> getMyList(PageRequestDTO pageRequestDTO, String writer);

}
