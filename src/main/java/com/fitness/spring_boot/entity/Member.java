package com.fitness.spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "GYMMember")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(nullable = false)
    private String mname;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;
//    @Column(nullable = false)
//    private String grade;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private String regDate;

    private String endDate;

    private int cnum;

    private String role;

//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    List<BoardEntity> boardEntityList = new ArrayList<>();

//    public static MemberEntity joinMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
//        MemberEntity memberEntity = new MemberEntity();
//        memberEntity.setMname(memberDTO.getMname());
//        memberEntity.setId(memberDTO.getId());
//        memberEntity.setPhone(memberDTO.getPhone());
//        memberEntity.setGender(memberDTO.getGender());
//        memberEntity.setAge(memberDTO.getAge());
//        String pass = passwordEncoder.encode(memberDTO.getPass());
//        memberEntity.setPass(pass);
//        memberEntity.setRole(Role.Member);
//
//        return memberEntity;
//    }
}
