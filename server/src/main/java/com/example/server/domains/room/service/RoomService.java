package com.example.server.domains.room.service;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    public Room findRoomByRoomId(String roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public Room createRoom(Room room) {
         return roomRepository.save(room);
    }

    public List<Room> findAllByMemberId(String memberId) {
        return roomRepository.findAllByMemberId(memberId);
    }
}
