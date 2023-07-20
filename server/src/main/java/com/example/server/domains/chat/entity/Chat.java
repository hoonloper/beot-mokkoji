package com.example.server.domains.chat.entity;

import com.example.server.domains.chat.vo.ChatEventType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "chats")
@Builder
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column
    private final String roomId;

    @Column
    private final String senderId;

    @Column
    private final String message;

    @Enumerated
    private final ChatEventType type;

    @Column
    private final LocalDateTime sendAt;
}
