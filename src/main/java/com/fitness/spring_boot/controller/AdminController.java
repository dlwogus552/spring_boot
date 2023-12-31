package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.admin.AdminService;
import com.fitness.spring_boot.Service.member.MemberService;
import com.fitness.spring_boot.Service.qna.QNABoardService;
import com.fitness.spring_boot.dto.PageRequestDTO;
import com.fitness.spring_boot.dto.PageResponseDTO;
import com.fitness.spring_boot.dto.qna.QNABoardDTO;
import com.fitness.spring_boot.entity.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {
    private final MemberService memberService;
    private final AdminService adminService;
    private final QNABoardService qnaBoardService;


    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("list", memberService.list());
        return "/admin/admin";
    }

    @GetMapping({"/memberview" ,"/membermodify"})
    public void get(@RequestParam Long mno, Model model) { model.addAttribute("member", memberService.findById(mno)); }
    @GetMapping("/qnalist")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<QNABoardDTO> responseDTO = qnaBoardService.getNoAnswerList(pageRequestDTO);
        log.info(pageRequestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
    @PostMapping("/membermodify")
    public String modify(Member member){
        Long mno=adminService.update(member);
        if(mno != null){
            return "redirect:/admin/memberview?mno="+mno;
        }
        return "rediect:/admin/membermodify?mno="+mno;
    }

    @GetMapping("/remove")
    public String remove (Long mno){
        memberService.delete(mno);
        return "redirect:/admin/";
    }

}
