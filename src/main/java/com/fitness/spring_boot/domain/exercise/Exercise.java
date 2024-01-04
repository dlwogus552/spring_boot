package com.fitness.spring_boot.domain.exercise;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
// NoArgsConstructor , AllArgsConstructor 이런거 이렇게 선언하면 public 으로 생성되서 좋은 방법이 아님
// @NoArgsConstructor(access = AccessLevel.PROTECTED) 이것처럼 다른 개발자가 생성을 못하도록 막던가 적절하게 접근제어자를 설정하는게 좋음
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "exercise")
@EntityListeners(value = {AuditingEntityListener.class})
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이거 왜 IDENTITY 인줄 아나 Mysql 은 이거고 DB마다 다름 인지하는게 좋음
    private Long eno;
    @Column(length = 45)
    private String title;
    @Lob
    private String content;
    private String part;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    @ColumnDefault("0")
    // 이런거 할때 원시 타입, 래퍼 클래스 적절하게 쓰면 좋음 예를 들어서 원시 타입은 null 이 없는거 기본값이 0 이였나 그런거
    // 래퍼 클래스는 클래스 이기 때문에 Heap 메모리 영역에 생성되기 때문에 최대의 성능을 내고 싶으면 적절하게 사용하는게 좋은데 막 그렇게 크진않음. 래퍼 클래스 공부 ㄱㄱ 왜쓰는지
    private int visitcount;

    public void updateVisitcount(){// 이런거 사소하긴 한데 카멜 케이스 지킬거면 똑바로 하는게 좋음
        this.visitcount+=1;
    }
    public void change(String title, String content, String part){
        this.title=title;
        this.content=content;
        this.part=part;
    }
}
