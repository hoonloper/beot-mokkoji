package com.example.server.domains.chat.interfaces;

import com.example.server.domains.chat.vo.ChatEventType;

import java.time.LocalDateTime;

public interface ChatsInterface {
    Long getId();
    String getSenderId();
    String getMessage();
    ChatEventType getType();
    LocalDateTime getSendAt();
}
