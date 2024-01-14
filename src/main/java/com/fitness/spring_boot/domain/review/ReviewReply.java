package com.fitness.spring_boot.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fitness.spring_boot.dto.review.ReviewReplyRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@ToString(exclude = "reviewboard")
public class ReviewReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rrno;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 100)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rno")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Review review;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewReply parent;

    private Long root;
    private Long leftNum=1L;
    private Long rightNum=2L;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    private List<ReviewReply> children = new ArrayList<>();


    @Builder
    public ReviewReply(ReviewReplyRequestDTO reviewReplyRequestDTO){
        this.content = reviewReplyRequestDTO.getContent();
        this.writer=reviewReplyRequestDTO.getWriter();
    }

    public void setChild(ReviewReply child){
        child.root=this.root;
        child.parent = this;
        child.leftNum=this.leftNum+1;
        child.rightNum = this.leftNum+1;
        this.children.add(child);
    }

    public void setRootId(ReviewReply reviewReply){
        this.root = reviewReply.getRrno();
    }
}
