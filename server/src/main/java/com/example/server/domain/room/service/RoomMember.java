package com.example.server.domain.room.service;


import lombok.Data;

@Data
public class RoomMember {
    private Long id;
    private String memberId;
    private String memberName;
    private String memberNickname;

    public RoomMember(Long id, String memberId, String memberName, String memberNickname) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
    }
}