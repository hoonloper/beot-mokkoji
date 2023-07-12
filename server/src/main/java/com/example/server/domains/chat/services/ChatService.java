package com.example.server.domains.chat.services;

import com.example.server.domains.chat.dto.ChatDto;
import com.example.server.domains.chat.entity.Chat;
import com.example.server.domains.chat.interfaces.ChatsInterface;
import com.example.server.domains.chat.repository.ChatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {
    private final ObjectMapper mapper;
    private final ChatRepository chatRepository;


    public void save(ChatDto chat) {
        chatRepository.save(new Chat(chat.getRoomId(), chat.getSenderId(), chat.getMessage(), chat.getType(), LocalDateTime.now()));
    }

    public List<ChatsInterface> findAllByRoomId(String roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
