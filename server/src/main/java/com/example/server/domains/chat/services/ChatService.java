package com.example.server.domains.chat.services;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {
    private final ObjectMapper mapper;
    private final RoomRepository roomRepository;

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public void setRooms(List<ChatRoom> rooms) {
        rooms.forEach(e -> chatRooms.putIfAbsent(e.getRoomId(), e));
    }

    public void setRoom(ChatRoom room) {
        chatRooms.putIfAbsent(room.getRoomId(), room);
    }

    public ChatRoom createRoom(String name, String memberId) {
        String roomId = UUID.randomUUID().toString(); // 랜덤한 방 아이디 생성

        // Builder 를 이용해서 ChatRoom 을 Building
        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .memberId(memberId)
                .build();
        roomRepository.save(new Room(room.getMemberId(), room.getRoomId(), room.getName()));

        setRoom(room); // 랜덤 아이디와 room 정보를 Map 에 저장
        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
