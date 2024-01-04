package com.fitness.spring_boot.domain.review;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "reviewboard")
public class ReviewReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rrno;
    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewBoard reviewBoard;

    private String replyText;
    @Column(name = "postdate", updatable = false)
    private LocalDateTime postDate;
    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }
}
