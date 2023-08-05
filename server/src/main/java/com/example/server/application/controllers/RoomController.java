package com.example.server.application.controllers;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public Room findRoomByRoomId(@PathVariable("roomId") @Valid @NotNull String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }

    @GetMapping("/beot/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Room> findAllByMemberId(@PathVariable("memberId") @Valid @NotNull String memberId) {
        return roomService.findAllByMemberId(memberId);
    }
}
