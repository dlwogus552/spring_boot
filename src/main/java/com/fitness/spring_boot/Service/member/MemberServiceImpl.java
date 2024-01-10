package com.fitness.spring_boot.Service.member;

import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long mno) {
        return memberRepository.findById(mno).get();
    }

    @Override
    public void update(Member member) {
        Member m=memberRepository.findById(member.getMno()).orElse(null);
        if (m != null) {
            m.setPhone(member.getPhone());
            memberRepository.save(m);
        }
    }

    @Override
    public void delete(Long mno) {
        memberRepository.deleteById(mno);
        SecurityContextHolder.clearContext();
    }


    @Override
    public Member findBymId(String mname) {
        return memberRepository.findBymId(mname);
    }


}
