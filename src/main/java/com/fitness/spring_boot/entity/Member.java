package com.fitness.spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
    @OnDelete(action= OnDeleteAction.CASCADE)
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true, name = "end_date")
    private Date endDate;


    private String role;



    public void change(String phone, Date endDate, String role){
        this.phone=phone;
        this.endDate=endDate;
        this.role=role;
    }


}
