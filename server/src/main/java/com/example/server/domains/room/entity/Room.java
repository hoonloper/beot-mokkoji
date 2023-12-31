package com.example.server.domains.room.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="rooms")
public class Room {
    @Id
    private String id;

    private List<RoomMember> members;

    @Field
    private String name;
}
