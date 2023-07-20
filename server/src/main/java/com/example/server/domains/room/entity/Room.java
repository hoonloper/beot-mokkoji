package com.example.server.domains.room.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;


@Entity(name = "rooms")
@Builder
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private final Member member;

    @Column(name = "member_id", nullable = false)
    private final String memberId;

    @Column
    private final String roomId;

    @Column
    private final String name;
}
