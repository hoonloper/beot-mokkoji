package com.example.server.domains.beot.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "beots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Beot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id", insertable = false, updatable = false)
    private Member fromMember;

    @Column(name = "from_member_id", nullable = false)
    private String fromMemberId;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id", insertable = false, updatable = false)
    private Member toMember;

    @Column(name = "to_member_id", nullable = false)
    private String toMemberId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Beot(String fromMemberId, String toMemberId, LocalDateTime createdAt) {
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.createdAt = createdAt;
    }

    public Beot(Long id, String fromMemberId, String toMemberId) {
        this.id = id;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
    }
}
