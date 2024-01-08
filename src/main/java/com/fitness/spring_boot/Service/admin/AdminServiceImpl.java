package com.fitness.spring_boot.Service.admin;


import com.fitness.spring_boot.entity.Member;
import com.fitness.spring_boot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final MemberRepository memberRepository;
    @Override
    public Member findById(Long mno) {
        return memberRepository.findById(mno).get();
    }

    @Override
    public void update(Member member) {
        Member m=memberRepository.findById(member.getMno()).get();
        m.setPhone(m.getPhone());
        m.setCnum(m.getCnum());
        m.setEndDate(m.getEndDate());
        m.setRole(m.getRole());
        memberRepository.save(m);
    }

    @Override
    public void delete(Long mno) {
        memberRepository.deleteById(mno);

    }
}
