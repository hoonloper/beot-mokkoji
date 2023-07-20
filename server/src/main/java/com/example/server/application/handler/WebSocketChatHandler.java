package com.example.server.application.handler;

import com.example.server.domain.chat.vo.ChatVo;
import com.example.server.domain.chat.vo.ChatEventType;
import com.example.server.domain.room.service.RoomService;
import com.example.server.domain.room.vos.RoomVo;
import com.example.server.domain.chat.services.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;
    private final ChatService chatService;
    private final RoomService roomService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);

        ChatVo chat = mapper.readValue(payload, ChatVo.class);
        log.info("session {}", chat.toString());

        RoomVo room = roomService.findRoomById(chat.getRoomId());
        log.info("room {}", room.toString());

        if (chat.getType().equals(ChatEventType.CONNECT) && !room.containsSession(session)) {
            room.addSession(session);
            chat.setMessage(chat.getSenderId() + " 님이 입장하셨습니다.");
            room.sendMessage(chat, chatService);
        } else if (chat.getType().equals(ChatEventType.MESSAGE)) {
            chat.setMessage(chat.getMessage());
            room.sendMessage(chat, chatService);
        } else if (chat.getType().equals(ChatEventType.DISCONNECT)) {
            chat.setMessage(chat.getSenderId() + " 님이 퇴장하셨습니다.");
            room.sendMessage(chat, chatService);
            room.removeSession(session);
        }
        chatService.save(chat);
    }
}