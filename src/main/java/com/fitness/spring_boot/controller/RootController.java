package com.fitness.spring_boot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller public class RootController {
    @GetMapping({"/contact", "classes", "blog", "about", "main", "index2"})
    public void go() {
    }

    @GetMapping("/")
    public String goIndex() {

        // 현재 인증된 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities){
        if (authority.getAuthority().equals("관리자")){
            return "redirect:/admin/";
        }
        }
        return "/index";
    }
}