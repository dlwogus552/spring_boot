package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.qna.QNAAnswerService;
import com.fitness.spring_boot.Service.qna.QNABoardFileService;
import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.config.auth.PrincipalDetails;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNAAnswerDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.dto.qna.QNABoardFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(value = "/download/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent
            , @PathVariable("filename") String filename) {
        log.info("download file: " + filename);
        Resource resource = new FileSystemResource(uploadPath+"\\qnafile\\"+filename);
        if (resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("resource: " + resource);
        String resourceName = resource.getFilename();
        String resourceOriginalName = resourceName.substring(resourceName.lastIndexOf("_") + 1);
        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadName = null;
            // 한글 파일이름 처리
            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8")
                        .replaceAll("\\+", " ");
            } else if (userAgent.contains("Edge")) {
                log.info("Edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            } else {
                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }
            headers.add("Content-Disposition", "attachment; filename=" + downloadName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/mylist") // 이 코드 Member쪽에 옮겨서 MyPage에 추가해주세요
    public void getMyQnAList(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String writer = principalDetails.getUsername();
        PageResponseDTO<QNABoardDTO> responseDTO = service.getMyList(pageRequestDTO, writer);
        log.info(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
}
