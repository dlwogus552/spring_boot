package com.fitness.spring_boot.Service.admin;


import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final MemberRepository memberRepository;
    @Override
    public Member findById(Long mno) {
        return memberRepository.findById(mno).get();
    }

    @Override
    public Long update(Member member) {
        Optional<Member> result=memberRepository.findById(member.getMno());
        Member member1 = result.orElseThrow();
        member1.change(member.getPhone(), member.getEndDate(), member.getRole());
        Long mno=memberRepository.save(member1).getMno();
        return mno;
    }

    @Override
    public void delete(Long mno) {
        memberRepository.deleteById(mno);

    }
}
