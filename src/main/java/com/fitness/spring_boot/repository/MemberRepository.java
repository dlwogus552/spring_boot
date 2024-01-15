package com.fitness.spring_boot.repository;

import com.fitness.spring_boot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.mId =:mId")
    Member findBymId(String mId);

//    @Query(value = "select m.role from member m where m.id = :id", nativeQuery = true)
//    String getRoleById(String id);
//
//    @Query(value = "select COUNT(*) from member m where m.id = :id", nativeQuery = true)
//    int idChk(String id);
//
//    @Query(value = "select * from member m where m.role = '일반회원'", nativeQuery = true)
//    List<Member> findAll();
}
