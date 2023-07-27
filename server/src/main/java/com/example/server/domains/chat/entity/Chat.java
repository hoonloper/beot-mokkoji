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

    @Field(name = "sender_idx")
    private String senderIdx;

    @Field(name = "sender_name")
    private String senderName;

    @Field(name = "receiver_idx")
    private String receiverIdx;

    @Field(name = "room_num")
    private Long roomNum;

    @Field(name = "created_at")
    private String createdAt;
}