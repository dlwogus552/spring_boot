package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/QnA")
@Log4j2
@Controller
@RequiredArgsConstructor
public class QNABoardController {
    private final QNABoardService service;

    @GetMapping("/register")
    public void QnABoardWrite() {

    }
    @PostMapping("/register")
    public String QnABoardWrite(QNABoardDTO qnaBoardDTO) {
        Long qnabno = service.register(qnaBoardDTO);
        if(qnabno != null) {
            log.info("board 작성 됨 : "+qnabno);
            return "redirect:/QnA/list";
        }
        return "redirect:/QnA/write";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<QNABoardDTO> responseDTO = service.getList(pageRequestDTO);
        log.info(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
}
