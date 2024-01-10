package com.fitness.spring_boot.Service.member;

import com.fitness.spring_boot.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
    public List<Member> list();
    public Member findById(Long mno);
    public void update(Member member);
    public void delete(Long mno);
    public Member findBymId(String mId);


}
