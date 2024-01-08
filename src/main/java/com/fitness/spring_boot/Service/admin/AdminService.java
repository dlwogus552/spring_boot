package com.fitness.spring_boot.Service.admin;

import com.fitness.spring_boot.entity.Member;

public interface AdminService {
    public Member findById(Long mno);
    public void update(Member member);
    public void delete(Long mno);
}
