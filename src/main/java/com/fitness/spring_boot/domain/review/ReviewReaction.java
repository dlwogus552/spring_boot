package com.fitness.spring_boot.domain.review;

import com.fitness.spring_boot.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rano;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="mno")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="rno")
    private Review review;

}
