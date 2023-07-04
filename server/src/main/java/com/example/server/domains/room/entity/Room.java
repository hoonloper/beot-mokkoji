package com.example.server.domains.room.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Room {
    @Id
    private String id;

    @Column
    private String memberId;

    @Column
    private String name;
}
