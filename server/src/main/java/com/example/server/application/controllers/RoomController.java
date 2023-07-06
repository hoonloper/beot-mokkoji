package com.example.server.application.controllers;

import com.example.server.domains.chat.services.ChatRoom;
import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.interfaces.FindAllByMemberIdInterface;
import com.example.server.domains.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domains.room.service.RoomGroup;
import com.example.server.domains.room.service.RoomService;
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

    @GetMapping("{memberId}")
    public List<RoomGroup> getAllRoomsByMemberId(@PathVariable("memberId") String memberId) {
        return roomService.getAllRoomsByMemberId(memberId);
    }

    @GetMapping("room/{roomId}")
    public List<FindAllByRoomIdInterface> findRoomByRoomId(@PathVariable("roomId") String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }
}
