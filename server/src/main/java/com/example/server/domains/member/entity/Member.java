package com.example.server.domains.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity(name = "members")
@Builder
@Getter
public class Member {
    @Id
    private final String id;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    private final String nickname;

    @Column
    private final LocalDate birthday;

}
