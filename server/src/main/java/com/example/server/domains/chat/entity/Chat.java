package com.example.server.domains.chat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Document(collection="chats")
public class Chat {

    @Id
    private String id;

    private String message;

    @Field(name = "sender_id")
    private String senderId;

    @Field(name = "sender_name")
    private String senderName;

    @Field(name = "receiver_id")
    private String receiverId;

    @Field(name = "room_id")
    private String roomId;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "message: " + message + "\n" +
                "sender_id: " + senderId + "\n" +
                "receiver_id: " + receiverId + "\n" +
                "room_id: " + roomId + "\n" +
                "created_at: " + createdAt + "\n";
    }

    public Chat(String id, String message, String senderId, String senderName, String receiverId, String roomId, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.roomId = roomId;
        this.createdAt = createdAt;
    }
}