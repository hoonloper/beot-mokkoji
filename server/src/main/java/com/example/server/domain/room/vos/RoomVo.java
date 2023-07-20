package com.example.server.domain.room.vos;

import com.example.server.domain.chat.services.ChatService;
import com.example.server.domain.room.entity.Room;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Data
public class RoomVo {
    private String roomId; // 채팅방 아이디
    private String memberId; // 채팅방 멤버 아이디
    private String name; // 채팅방 이름
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public RoomVo(String roomId, String name, String memberId){
        this.roomId = roomId;
        this.name = name;
        this.memberId = memberId;
    }

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