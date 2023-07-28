package com.example.server.domains.room.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("room_members")
public class RoomMember {
    @Id
    private String id;
    private String name;
    private String nickname;

    @Field("member_id")
    private String memberId;
}
