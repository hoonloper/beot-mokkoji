package com.example.server.domains.chat.entity;

import com.example.server.domains.chat.dto.ChatEventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "chats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Chat {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String roomId;

    @Column
    private String senderId;

    @Column
    private String message;

    @Enumerated
    private ChatEventType eventType;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
