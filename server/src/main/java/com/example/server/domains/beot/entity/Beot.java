package com.example.server.domains.beot.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "beots")
@Data
public class Beot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "from_member_id", insertable = false, updatable = false)
    private Member fromMember;

    @Column(name = "from_member_id")
    private String fromMemberId;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "to_member_id", insertable = false, updatable = false)
    private Member toMember;

    @Column(name = "to_member_id")
    private String toMemberId;

    @Column
    private LocalDateTime createdAt;
}
