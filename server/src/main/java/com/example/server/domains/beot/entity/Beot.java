package com.example.server.domains.beot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "beots")
@Data
public class Beot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fromMemberId;

    @Column
    private String toMemberId;

    @Column
    private LocalDateTime createdAt;
}
