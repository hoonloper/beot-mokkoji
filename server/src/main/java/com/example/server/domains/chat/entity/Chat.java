package com.example.server.domains.chat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Data
@Document(collection="chats")
public class Chat {

    @Id
    private String id;

    private String msg;

    @Field(name = "sender_id")
    private String senderId;

    @Field(name = "sender_name")
    private String senderName;

    @Field(name = "receiver_id")
    private String receiverId;

    @Field(name = "room_id")
    private String roomId;

    @Field(name = "created_at")
    private String createdAt;

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "msg: " + msg + "\n" +
                "sender_id: " + senderId + "\n" +
                "receiver_id: " + receiverId + "\n" +
                "room_id: " + roomId + "\n" +
                "created_at: " + createdAt + "\n";
    }

    @Builder
    public Chat(String id, String msg, String senderId, String senderName, String receiverId, String roomId, String createdAt) {
        this.id = id;
        this.msg = msg;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.roomId = roomId;
        this.createdAt = createdAt;
    }
}