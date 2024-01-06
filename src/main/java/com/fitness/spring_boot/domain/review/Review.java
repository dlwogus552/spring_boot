package com.fitness.spring_boot.domain.review;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String title;
    @Lob
    private String content;
    private String writer;
    @ColumnDefault("0")
    @Column(name="bestcount")
    private int bestCount;
    @ColumnDefault("0")
    @Column(name="visitcount")
    private int visitCount;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void updateBestcount(){
        this.bestCount+=1;
    }
    public void updateVisitcount(){
        this.visitCount+=1;
    }
}
