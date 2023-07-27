package com.example.server.domains.room.service;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.repository.RoomRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public Mono<Room> findRoomByRoomId(String roomId) {
        return roomRepository.findById(roomId);
    }

    public Mono<Room> createRoom(Room room) {
         return roomRepository.save(room);
    }

}
