package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.qna.QNABoardFileService;
import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private final QNABoardFileService fileService;

    @GetMapping("/register")
    public void QnABoardWrite() {

    }
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void QnABoardWrite(QNABoardDTO qnaBoardDTO) {
        Long qnabno = service.register(qnaBoardDTO);
        if(qnabno != null) {
            log.info("board 작성 됨 : "+qnabno);
//            return "redirect:/QnA/list";
        }
//        return "redirect:/QnA/write";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<QNABoardDTO> responseDTO = service.getList(pageRequestDTO);
        log.info(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping({"/view", "/modify"})
    public void view(PageRequestDTO pageRequestDTO, Model model, Long qnabno) {
        model.addAttribute("fileList", fileService.getFileList(qnabno));
        model.addAttribute("dto", service.getBoard(qnabno));
    }

    @Transactional
    @PostMapping(value = "/modify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String modify(PageRequestDTO pageRequestDTO, QNABoardDTO qnaBoardDTO) {
        service.modify(qnaBoardDTO);
        if (qnaBoardDTO.getFiles() != null && !qnaBoardDTO.getFiles().get(0).isEmpty()) {
            fileService.FileDeleteAll(qnaBoardDTO.getQnabno());
            fileService.FileUpload(qnaBoardDTO);
        }
        return "redirect:/QnA/view?qnabno=" + qnaBoardDTO.getQnabno() + "&" + pageRequestDTO.getLink();
    }

    @GetMapping("/remove")
    @Transactional
    public String remove(Long qnabno) {
        service.remove(qnabno);
        return "redirect:/QnA/list";
    }
}
