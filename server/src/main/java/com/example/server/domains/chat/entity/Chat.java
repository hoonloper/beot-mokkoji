package com.example.server.domains.chat.entity;

import com.example.server.domains.chat.dto.ChatEventType;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "chats")
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roomId;

    @Column
    private String senderId;

    @Column
    private String message;

    @Enumerated
    private ChatEventType eventType;

    @Column
    private LocalDateTime sendAt;

    public Chat(String roomId, String senderId, String message, ChatEventType eventType, LocalDateTime sendAt) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.eventType = eventType;
        this.sendAt = sendAt;
    }
}
