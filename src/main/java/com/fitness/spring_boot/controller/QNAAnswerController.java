package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.qna.QNAAnswerService;
import com.fitness.spring_boot.Service.qna.QNABoardFileService;
import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.domain.qna.QNABoard;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.qna.QNAAnswerDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/QnA/answer")
@Log4j2
@Controller
@RequiredArgsConstructor
public class QNAAnswerController {
    private final QNABoardService boardService;
    private final QNAAnswerService answerService;
    private final QNABoardFileService fileService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public void register(PageRequestDTO pageRequestDTO, Model model, Long qnabno) {
        model.addAttribute("dto", boardService.getBoard(qnabno));
        model.addAttribute("fileList", fileService.getFileList(qnabno));
    }

    @PostMapping("/register")
    public String register(PageRequestDTO pageRequestDTO, QNAAnswerDTO answerDTO, Long qnabno) {
        QNABoardDTO qnaBoardDTO = boardService.getBoard(qnabno);
        QNABoard board = modelMapper.map(qnaBoardDTO, QNABoard.class);
        answerDTO.setQnaBoard(board);
        answerService.register(answerDTO);
        return "redirect:/QnA/view?qnabno=" + qnaBoardDTO.getQnabno() + "&" + pageRequestDTO.getLink();
    }
}
