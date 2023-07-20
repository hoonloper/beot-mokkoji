package com.example.server.domains.room.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;


@Entity(name = "rooms")
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column
    private String roomId;

    @Column
    private String name;

    public Room(String memberId, String roomId, String name) {
        this.memberId = memberId;
        this.roomId = roomId;
        this.name = name;
    }
}
