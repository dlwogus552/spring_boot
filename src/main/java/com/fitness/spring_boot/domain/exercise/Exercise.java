package com.fitness.spring_boot.domain.exercise;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int visitcount;

    public void updateVisitcount(){
        this.visitcount+=1;
    }
    public void change(String title, String content, String part){
        this.title=title;
        this.content=content;
        this.part=part;
    }
}
