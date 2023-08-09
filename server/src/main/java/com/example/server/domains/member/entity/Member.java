package com.example.server.domains.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE members SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Member {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Temporal(value = TemporalType.DATE)
    private LocalDate birthday;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;

    public Member(String id, String name, String nickname, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public Member(String id) {
        this.id = id;
    }
}
