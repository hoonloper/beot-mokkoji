package com.example.server.domain.chat.interfaces;

import com.example.server.domain.chat.vo.ChatEventType;

import java.time.LocalDateTime;

public interface ChatsInterface {
    Long getId();
    String getSenderId();
    String getMessage();
    ChatEventType getType();
    LocalDateTime getSendAt();
}
