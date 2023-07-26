package com.example.server.domains.chat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="chats")
public class Chat {
    @Id
    private String id;
    private String msg;
    private Integer sender_idx;
    private String sender_name;
    private Integer receiver_idx;
    private Integer room_num;
    private String created_at;
}