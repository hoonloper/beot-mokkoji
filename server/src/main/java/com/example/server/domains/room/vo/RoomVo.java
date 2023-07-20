package com.example.server.domains.room.vo;

import com.example.server.domains.chat.service.ChatService;
import com.example.server.domains.room.entity.Room;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Data
@Builder
public class RoomVo {
    private String roomId; // 채팅방 아이디
    private String memberId; // 채팅방 멤버 아이디
    private String name; // 채팅방 이름
    private Set<WebSocketSession> sessions = new HashSet<>();

    public <T> void sendMessage(T message, ChatService service) {
        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }
    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public boolean containsSession(WebSocketSession session) {
        return sessions.contains(session);
    }

    public static RoomVo toRoomVo(Room room) {
        return RoomVo.builder()
                .roomId(room.getRoomId())
                .memberId(room.getMemberId())
                .name(room.getName())
                .build();
    }
}