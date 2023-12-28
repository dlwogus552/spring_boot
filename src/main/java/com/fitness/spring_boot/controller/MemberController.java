package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/join")
    public void join(){

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




}
