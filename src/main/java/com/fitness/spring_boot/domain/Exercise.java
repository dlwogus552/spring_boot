package com.fitness.spring_boot.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eno")
    private Long id;
    @Column(length = 45)
    private String title;
    @Lob
    private String content;
    @Column(length = 200)
    private String thumbnail;
    @Column(length = 200)
    private String video;
    private String part;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    private int visitcount;

    public void updateVisitcount(){
        this.visitcount+=1;
    }
    public void change(String title, String content, String thumbnail, String video, String part){
        this.title=title;
        this.content=content;
        this.thumbnail=thumbnail;
        this.video=video;
        this.part=part;
    }
}
