package com.example.server.domains.beot.entity;

import com.example.server.domains.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "beots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Beot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_member_id", insertable = false, updatable = false)
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member_id", insertable = false, updatable = false)
    private Member toMember;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Beot(Long id) {
        this.id = id;
    }
}
