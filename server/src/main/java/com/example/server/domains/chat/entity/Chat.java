//package com.example.server.domains.chat.entity;
//
//import com.example.server.domains.chat.vo.ChatEventType;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Entity(name = "chats")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//public class Chat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String roomId;
//
//    @Column
//    private String senderId;
//
//    @Column
//    private String message;
//
//    @Enumerated
//    private ChatEventType type;
//
//    @Column
//    private LocalDateTime sendAt;
//
//    public Chat(String roomId, String senderId, String message, ChatEventType type, LocalDateTime sendAt) {
//        this.roomId = roomId;
//        this.senderId = senderId;
//        this.message = message;
//        this.type = type;
//        this.sendAt = sendAt;
//    }
//}
