package com.fitness.spring_boot.domain.review;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
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
    @ColumnDefault("0")
    @Column(name="westcount")
    private int westCount;
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

    public void updateBestCount(){
        this.bestCount+=1;
    }
    public void updateWestCount(){
        this.westCount+=1;
    }
    public void updateVisitCount(){
        this.visitCount+=1;
    }
}
