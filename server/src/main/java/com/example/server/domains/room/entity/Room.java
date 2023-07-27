package com.example.server.domains.room.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection="rooms")
public class Room {
    @Id
    private Long id;

    @Field(name = "member_id")
    private String memberId;

    @Field
    private String name;

    public Room(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
