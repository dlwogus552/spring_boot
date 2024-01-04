package com.fitness.spring_boot.domain.ptreserve;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PtBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;
    @Column(name = "postdate", updatable = false)
    private LocalDateTime postDate;
}
