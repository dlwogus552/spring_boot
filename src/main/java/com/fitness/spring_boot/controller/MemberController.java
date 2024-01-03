package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.MemberService;
import com.fitness.spring_boot.config.auth.PrincipalDetails;
import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/join")
    public void join(){


        //  현재(2024년 1월3일) 로그인상태에서 회원가입시 로그인상태그대로 가입진행하고 메인으로 이동
        //  조금 거슬려서 수정한다면 -> 로그인상태 - 가입진행 - 자동로그아웃된 상태로 메인페이지??? 일단보류 큰 이슈 아님

    }

    @PostMapping("/register")
    public String register(Member member){
        log.info(member);
        String pass = member.getPass();
        String enPass = bCryptPasswordEncoder.encode(pass);
        member.setPass(enPass);
        member.setRole("일반회원");
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public void get(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        Long mno = principalDetails.getMember().getMno();
        model.addAttribute("member", memberService.findById(mno));
    }

    @PostMapping("/modify")
    public String modify(Member member){
        memberService.update(member);
        return "redirect:/member/mypage";
        //리다이렉트 위치 설정 제대로 해야함
    }

    @GetMapping("/remove")
    public String remove(Long mno){
        memberService.delete(mno);
        return "redirect:/";
    }


}
