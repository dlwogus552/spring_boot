package com.fitness.spring_boot.Service;

import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
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
        Member m=memberRepository.findById(member.getMno()).get();
        m.setPhone(m.getPhone());
    }

    @Override
    public void delete(Long mno) {
        memberRepository.deleteById(mno);
        SecurityContextHolder.clearContext();

    }




}
