package com.fitness.spring_boot.domain.review;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String title;
    @Lob
    private String content;
    @Column(name = "postdate", updatable = false)
    private String writer;
    private String fileimage;
    @ColumnDefault("0")
    private int bestcount;
    @ColumnDefault("0")
    private int visitcount;
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    public void changeReview(String title, String content, String fileimage){
        this.title = title;
        this.content = content;
        this.fileimage = fileimage;
    }

    public void updateBestcount(){
        this.bestcount+=1;
    }
    public void updateVisitcount(){
        this.visitcount+=1;
    }
}
