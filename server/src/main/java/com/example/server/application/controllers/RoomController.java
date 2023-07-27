package com.example.server.application.controllers;

import com.example.server.domains.room.services.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createRoom(@RequestBody @Valid Object roomDto){
        return roomService.createRoom(roomDto);
    }

    @GetMapping
    public List<Object> findAllRooms(){
//        return roomService.findAllRoom();
        return new ArrayList<>();
    }

    @GetMapping("/{memberId}")
    public List<Object> getAllRoomsByMemberId(@PathVariable("memberId") @Valid @NotNull String memberId) {
        return roomService.getAllRoomsByMemberId(memberId);
    }

    @GetMapping("/room/{roomId}")
    public List<Object> findRoomByRoomId(@PathVariable("roomId") @Valid @NotNull String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }
}
