package com.example.server.domains.room.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection="rooms")
public class Room {
    @Id
    private String id;

    @Field(name = "member_id")
    private String memberId;

    @Field
    private String name;
}
