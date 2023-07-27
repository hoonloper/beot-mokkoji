package com.example.server.domains.room.services;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;


    public List<Object> findRoomByRoomId(String roomId) {
//        List<Object> rooms = roomRepository.findAllByRoomId(roomId);
//        rooms.stream().map(room -> RoomVo
//                        .builder()
//                        .roomId(room.getRoomId())
//                        .name(room.getName())
//                        .memberId(room.getMemberId())
//                        .build()).forEach(this::registerRoom);
//        return rooms;
        return new ArrayList<>();
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public Mono<Room> createRoom(Room room) {
         return roomRepository.save(room);
    }

}
