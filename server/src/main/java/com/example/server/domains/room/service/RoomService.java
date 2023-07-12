package com.example.server.domains.room.service;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.interfaces.FindAllByMemberIdInterface;
import com.example.server.domains.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domains.room.repository.RoomRepository;
import com.example.server.domains.room.vos.RoomVO;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private Map<String, RoomVO> roomVO;

    @PostConstruct
    private void init() {
        roomVO = new LinkedHashMap<>();
    }
    public List<FindAllByRoomIdInterface> findRoomByRoomId(String roomId) {
        List<FindAllByRoomIdInterface> rooms = roomRepository.findAllByRoomId(roomId);
        rooms.stream().map(room -> new RoomVO(room.getRoomId(), room.getName(), room.getMemberId())).forEach(this::registerRoom);
        return rooms;
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
    public RoomVO createRoom(String name, String memberId) {
        String roomId = UUID.randomUUID().toString(); // 랜덤한 방 아이디 생성
        RoomVO room = toRoomVO(roomRepository.save(new Room(memberId, roomId, name)));
        registerRoom(room); // 랜덤 아이디와 room 정보를 Map 에 저장
        return room;
    }

    public List<RoomVO> findAllRoom(){
        return new ArrayList<>(roomVO.values());
    }

    public RoomVO findRoomById(String roomId){
        return roomVO.get(roomId);
    }

    private void registerRoom(RoomVO room) {
        roomVO.putIfAbsent(room.getRoomId(), room);
    }
    private RoomVO toRoomVO(Room room) {
        return RoomVO.builder()
                .roomId(room.getRoomId())
                .memberId(room.getMemberId())
                .name(room.getName())
                .build();
    }
}
