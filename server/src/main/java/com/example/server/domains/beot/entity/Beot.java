package com.example.server.domains.beot.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "beots")
@Builder
@Getter
public class Beot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "from_member_id", insertable = false, updatable = false)
    private final Member fromMember;

    @Column(name = "from_member_id", nullable = false)
    private final String fromMemberId;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "to_member_id", insertable = false, updatable = false)
    private final Member toMember;

    @Column(name = "to_member_id", nullable = false)
    private final String toMemberId;

    @Column(name = "created_at", nullable = false)
    private final LocalDateTime createdAt;
}
