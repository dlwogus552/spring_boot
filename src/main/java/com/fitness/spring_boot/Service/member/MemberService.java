package com.fitness.spring_boot.Service.member;

import com.fitness.spring_boot.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    public List<Member> list();
    public Member findById(Long mno);
    public void update(Member member);
    public void delete(Long mno);
    // 프로필 사진 업로드 처리
    void uploadProfilePhoto(Long mno, MultipartFile file);

}
