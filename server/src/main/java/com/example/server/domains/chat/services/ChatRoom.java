package com.example.server.domains.chat.services;

import com.example.server.domains.chat.dto.ChatDto;
import com.example.server.domains.chat.dto.ChatEventType;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private String roomId; // 채팅방 아이디
    private String memberId; // 채팅방 멤버 아이디
    private String name; // 채팅방 이름
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name, String memberId){
        this.roomId = roomId;
        this.name = name;
        this.memberId = memberId;
    }

    public void handleAction(WebSocketSession session, ChatDto chat, ChatService chatService) {
        // chat 에 담긴 타입을 확인한다.
        // 이때 chat 에서 getType 으로 가져온 내용이
        // ChatDTO 의 열거형인 MessageType 안에 있는 CONNECT 과 동일한 값이라면
        if (chat.getType().equals(ChatEventType.CONNECT) && !sessions.contains(session)) {
            // sessions 에 넘어온 session 을 담고,
            sessions.add(session);

            // chat 에는 입장하였다는 메시지를 띄운다
            chat.setMessage(chat.getSenderId() + " 님이 입장하셨습니다.");
            sendMessage(chat, chatService);
        } else if (chat.getType().equals(ChatEventType.MESSAGE)) {
            chat.setMessage(chat.getMessage());
            sendMessage(chat, chatService);
        } else if (chat.getType().equals(ChatEventType.DISCONNECT)) {
            chat.setMessage(chat.getSenderId() + " 님이 퇴장하셨습니다.");
            sendMessage(chat, chatService);
            sessions.remove(session);
        }
        chatService.save(chat);
    }

    public <T> void sendMessage(T message, ChatService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }
}