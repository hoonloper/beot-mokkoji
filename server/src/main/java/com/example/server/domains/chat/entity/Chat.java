package com.example.server.domains.chat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
}