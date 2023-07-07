package com.example.server.application.handler;

import com.example.server.domains.chat.dto.ChatDto;
import com.example.server.domains.chat.services.ChatRoom;
import com.example.server.domains.chat.services.ChatService;
import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.service.RoomService;
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

        ChatDto chat = mapper.readValue(payload, ChatDto.class);
        log.info("session {}", chat.toString());
        // TODO: DB에서 채팅방 정보 가져와서 세션에 등록하는 코드 추가해야 함
        ChatRoom room = chatService.findRoomById(chat.getRoomId());
        System.out.println(chatService.findRoomById(chat.getRoomId()));
//        log.info("room {}", room.toString());

//            roomService.saveRoom(new Room(chat.getSenderId(), room.getRoomId(), room.getName()));

        room.handleAction(session, chat, chatService);
    }
}