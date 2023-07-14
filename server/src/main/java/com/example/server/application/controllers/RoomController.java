package com.example.server.application.controllers;

import com.example.server.domain.room.dtos.RoomDto;
import com.example.server.domain.room.vos.RoomVO;
import com.example.server.domain.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domain.room.service.RoomGroup;
import com.example.server.domain.room.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public RoomVO createRoom(@RequestBody @Valid RoomDto roomDto){
        return roomService.createRoom(roomDto);
    }

    @GetMapping
    public List<RoomVO> findAllRooms(){
        return roomService.findAllRoom();
    }

    @GetMapping("{memberId}")
    public List<RoomGroup> getAllRoomsByMemberId(@PathVariable("memberId") String memberId) {
        return roomService.getAllRoomsByMemberId(memberId);
    }

    @GetMapping("room/{roomId}")
    public List<FindAllByRoomIdInterface> findRoomByRoomId(@PathVariable("roomId") String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }
}
