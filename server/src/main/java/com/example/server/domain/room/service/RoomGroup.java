package com.example.server.domain.room.service;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomGroup {
    private String roomId;
    private String name;
    private List<RoomMember> roomMembers;

    public RoomGroup(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
        this.roomMembers = new ArrayList<>();
    }
}