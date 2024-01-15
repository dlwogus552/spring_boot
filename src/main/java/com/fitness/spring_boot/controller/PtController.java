package com.fitness.spring_boot.controller;


import com.fitness.spring_boot.Service.member.MemberService;
import com.fitness.spring_boot.Service.ptreserve.PtBoardService;
import com.fitness.spring_boot.Service.ptreserve.PtTrainerService;
import com.fitness.spring_boot.domain.ptreserve.PtBoard;
import com.fitness.spring_boot.domain.ptreserve.PtTrainer;
import com.fitness.spring_boot.dto.ptreserve.PtBoardDTO;
import com.fitness.spring_boot.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        Member member = memberService.findBymId(mId); // 멤버 받아옴
        log.info(member);
        ptBoardDTO.setMember(member);
        PtTrainer trainer = ptTrainerService.getTrainer(tno); // 트레이너 받아옴
        ptBoardDTO.setTrainer(trainer);
        ptBoardService.makeReservation(ptBoardDTO); // 저장
        return "성공";
    }
    @ResponseBody
    @PostMapping("/check")
    public List<String> checkBox(Date reserve, Long tno){
        List<PtBoardDTO> resultList = ptBoardService.chechReserve(reserve,tno);
        List<String> timeSlot = new ArrayList<>();
        if(resultList !=null && resultList.size()>0){
            for(PtBoardDTO result : resultList){
                timeSlot.add(result.getTimeSlot());
            }
            log.info(timeSlot);
            return timeSlot;
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/remove")
    public String remove(Date reserve, Long tno, String mId, Long pno) {
        log.info("remove컨트롤"+reserve);
        log.info("remove컨트롤"+tno);
        log.info("remove컨트롤"+mId);

        Long mno = memberService.findBymId(mId).getMno();

//        ptBoardService.remove(reserve,tno,mno);
        ptBoardService.removeById(pno);
        return "cancel";
    }

}
