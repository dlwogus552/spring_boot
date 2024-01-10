package com.fitness.spring_boot.controller;


import com.fitness.spring_boot.Service.member.MemberService;
import com.fitness.spring_boot.Service.ptreserve.PtBoardService;
import com.fitness.spring_boot.Service.ptreserve.PtTrainerService;
import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        return "redirect:/ptreserve/Trainer";
    }

    @GetMapping("/trainer")
    public void trainer(){
    }

    @GetMapping("/calendar")
    public void calendar(Long tno, Model model){

        model.addAttribute("tno", tno);
    }
    @ResponseBody
    @PostMapping(value = "/calendar")
    public String Ptreserve(PtBoardDTO ptBoardDTO, String mId,Long tno){
        log.info("mname : " + mId);
        Member member = memberService.findBymId(mId); // 멤버 받아옴
        log.info(member);
        ptBoardDTO.setMember(member);
        log.info("ptBoard : " + ptBoardDTO);
        log.info("ptBoardDTO.getTno : " + tno);
        PtTrainer trainer = ptTrainerService.getTrainer(tno); // 트레이너 받아옴
        ptBoardDTO.setTrainer(trainer);
        ptBoardService.makeReservation(ptBoardDTO); // 저장
        return "성공";
    }
    @ResponseBody
    @PostMapping("/check")
    public List<String> checkBox(Date reserve, Long tno){
        log.info("ptBoarDTO : "+reserve);
        log.info("tno : "+tno);
        List<PtBoardDTO> resultList = ptBoardService.chechReserve(reserve,tno);
        log.info("서비스 후 : "+resultList);
        List<String> timeSlot = new ArrayList<>();
        if(resultList !=null && resultList.size()>0){
            for(PtBoardDTO result : resultList){
                log.info("for 안에 : "+result.getTimeSlot());
                timeSlot.add(result.getTimeSlot());
            }
            log.info(timeSlot);
            return timeSlot;
        }
        return null;
    }

}
