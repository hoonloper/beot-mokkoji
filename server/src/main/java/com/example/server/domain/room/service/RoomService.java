package com.example.server.domain.room.service;

import com.example.server.domain.room.dtos.RoomDto;
import com.example.server.domain.room.entity.Room;
import com.example.server.domain.room.interfaces.FindAllByMemberIdInterface;
import com.example.server.domain.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domain.room.repository.RoomRepository;
import com.example.server.domain.room.vos.RoomVO;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Profile("prod")
@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private Map<String, RoomVO> roomVO;

    // TODO: PostConstruct Profile을 이용해서 테스트 환경 분리
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
    public RoomVO createRoom(RoomDto roomDto) {
        String roomId = UUID.randomUUID().toString(); // 랜덤한 방 아이디 생성
        RoomVO room = toRoomVO(roomRepository.save(new Room(roomDto.memberId(), roomId, roomDto.name())));
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
