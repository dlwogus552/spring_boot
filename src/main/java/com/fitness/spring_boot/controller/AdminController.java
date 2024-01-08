package com.fitness.spring_boot.controller;

import com.fitness.spring_boot.Service.MemberService;
import com.fitness.spring_boot.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;



    @GetMapping("/memberlist")
    public void list(Model model) { model.addAttribute("list", memberService.list()); }

    @GetMapping("/memberview")
    public void get(@RequestParam Long mno, Model model) { model.addAttribute("member", memberService.findById(mno)); }

    @PostMapping("/membermodify")
    public String modify(Member member) {
        memberService.update(member);
        return "redirect:/admin/memberview?num="+member.getMno();
    }

    @GetMapping("/remove")
    public String remove (Long mno){
        memberService.delete(mno);
        return "redirect:/admin/memberlist";
    }

}
