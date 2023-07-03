package com.example.server.domains.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private LocalDate birthday;

}
