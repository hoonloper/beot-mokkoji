package com.example.server.application.controllers;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.services.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
    public Mono<Room> createRoom(@RequestBody @Valid Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("/room/{roomId}")
    public List<Object> findRoomByRoomId(@PathVariable("roomId") @Valid @NotNull String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }
}
