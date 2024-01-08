package com.fitness.spring_boot.domain.review;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    @JoinColumn(name="rno")
    private Review review;
    private String replyText;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    @CreatedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }
}
