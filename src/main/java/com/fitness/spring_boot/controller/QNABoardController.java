package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.qna.QNAAnswerService;
import com.fitness.spring_boot.Service.qna.QNABoardFileService;
import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNAAnswerDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("/QnA")
@Log4j2
@Controller
@RequiredArgsConstructor
public class QNABoardController {
    private final QNABoardService service;
    private final QNABoardFileService fileService;
    private final QNAAnswerService answerService;

    @Value("${com.fitness.spring_boot.upload.path}")
    private String uploadPath;

    @GetMapping("/register")
    public void QnABoardWrite() {

    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void QnABoardWrite(QNABoardDTO qnaBoardDTO) {
        Long qnabno = service.register(qnaBoardDTO);
        if (qnabno != null) {
            log.info("board 작성 됨 : " + qnabno);
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
        QNAAnswerDTO answerDTO = answerService.getAnswer(qnabno);
        if (answerDTO != null) {
            model.addAttribute("answer", answerService.getAnswer(qnabno));
        }
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

    @GetMapping("/download/{qnafno}")
    @ResponseBody
    public ResponseEntity<Object> downloadFile(@PathVariable Long qnafno) {
        QNABoardFileDTO fileDTO = fileService.getFile(qnafno);
        String filePath = uploadPath + "\\qnafile\\";
        File file = new File(filePath + fileDTO.getUuid() + "_" + fileDTO.getFilename());
        if (file.exists()) {
            filePath = filePath + fileDTO.getUuid() + "_" + fileDTO.getFilename();
            log.info("filePath : "+filePath);
            return fileService.fileDownload(filePath);
        } else {
            return null;
        }
    }
}
