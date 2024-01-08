package com.fitness.spring_boot.controller;


import com.fitness.spring_boot.Service.MemberService;
import com.fitness.spring_boot.Service.ptreserve.PtBoardService;
import com.fitness.spring_boot.Service.ptreserve.PtTrainerService;
import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    private final PtBoardService ptBoardService;

    @GetMapping("/trainerInit")
    public String trainerInit(){
        ptTrainerService.initPtTrainer();
        return "redirect:/ptreserve/selTrainer";
    }

    @GetMapping("/trainer")
    public void trainer(){
    }

    @GetMapping("/calendar")
    public void calendar(Long tno, Model model){
        model.addAttribute("tno", tno);
    }

    @PostMapping("/calendar")
    public String Ptreserve(PtBoardDTO ptBoardDTO, String mId){
        log.info("mname : " + mId);
        Member member = memberService.findBymId(mId); // 멤버 받아옴
        log.info(member);
        ptBoardDTO.setMember(member);
        log.info("ptBoard : " + ptBoardDTO);
        log.info("ptBoardDTO.getTno : " + ptBoardDTO.getTno());
        PtTrainer trainer = ptTrainerService.getTrainer(ptBoardDTO.getTno()); // 트레이너 받아옴
        ptBoardDTO.setTrainer(trainer);

        ptBoardService.makeReservation(ptBoardDTO); // 저장
        return null;
    }

}
