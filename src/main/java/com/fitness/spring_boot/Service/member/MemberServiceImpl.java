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
    public void uploadProfilePhoto(Long mno, MultipartFile file) {
        Member member = memberRepository.findById(mno).orElse(null);

        if (member != null && !file.isEmpty()) {
            try {
                // 파일을 저장할 경로 설정
                String uploadDir = "c:/upload/member";
                File folder = new File(uploadDir);
                if (!folder.exists()) {
                    try {
                        folder.mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Path uploadPath = Paths.get(uploadDir);

                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }

                // 파일 이름 설정 (여기서는 간단히 현재 시간을 사용)
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                // 파일 저장
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // DB에 파일 이름 저장
                member.setProfilePhoto(fileName);
                memberRepository.save(member);
                log.info("프로필 사진 업로드 완료. 파일 경로: {}", filePath.toString());
            } catch (IOException e) {
                log.error("프로필 사진 업로드 실패", e);
            }
        }
    }


}
