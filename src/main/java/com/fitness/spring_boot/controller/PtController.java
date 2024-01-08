package com.fitness.spring_boot.controller;


import com.fitness.spring_boot.Service.MemberService;
import com.fitness.spring_boot.Service.ptreserve.PtTrainerService;
import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/ptreserve")
public class PtController {
    private final PtTrainerService ptTrainerService;
    private final MemberService memberService;

    @GetMapping("/trainerInit")
    public String trainerInit(){
        ptTrainerService.initPtTrainer();
        return "redirect:/ptreserve/selTrainer";
    }

    @GetMapping("/trainer")
    public void trainer(){
    }

    @GetMapping("/calendar")
    public void calendar(int tno, Model model){
        model.addAttribute("tno", tno);
    }

    @PostMapping("/calendar")
    public String Ptreserve(PtBoard ptBoard, String mname){
        log.info("mname : " + mname);
        log.info("ptBoard : " + ptBoard);
        Member member = memberService.findBymId(mname);
        return null;
    }

}
