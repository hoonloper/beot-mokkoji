package com.example.server.domains.room.service;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.interfaces.FindAllByMemberIdInterface;
import com.example.server.domains.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domains.room.repository.RoomRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public List<FindAllByRoomIdInterface> findRoomByRoomId(String roomId) {
        return roomRepository.findAllByRoomId(roomId);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public List<RoomGroup> getAllRoomsByMemberId(String memberId) {
        List<FindAllByMemberIdInterface> rooms = roomRepository.findAllByMemberId(memberId);

        List<RoomGroup> roomGroups = new ArrayList<>();
        for (FindAllByMemberIdInterface room : rooms) {
            RoomMember roomMember = new RoomMember(room.getId(), room.getMemberId(), room.getMemberName(), room.getMemberNickname());
            RoomGroup roomGroup = roomGroups.stream()
                    .filter(group -> group.getRoomId().equals(room.getRoomId()))
                    .findFirst()
                    .orElseGet(() -> {
                        RoomGroup newGroup = new RoomGroup(room.getRoomId(), room.getName());
                        roomGroups.add(newGroup);
                        return newGroup;
                    });

            roomGroup.getRoomMembers().add(roomMember);
        }

        return roomGroups;
    }

}
