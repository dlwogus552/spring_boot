package com.fitness.spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "GYMMember")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(nullable = false)
    private String mname;

    @Column(nullable = false, unique = true)
    private String mId;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date regDate;

    @Column(nullable = true)
    private Date endDate;

    @Column(nullable = true)
    private int cnum;

    private String role;

    @Column(nullable = true)
    private String profilePhoto;

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }


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
