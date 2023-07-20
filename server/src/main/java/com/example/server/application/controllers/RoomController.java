package com.example.server.application.controllers;

import com.example.server.domains.room.dto.RoomDto;
import com.example.server.domains.room.vo.RoomVo;
import com.example.server.domains.room.interfaces.FindAllByRoomIdInterface;
import com.example.server.domains.room.services.RoomGroup;
import com.example.server.domains.room.services.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomVo createRoom(@RequestBody @Valid RoomDto roomDto){
        return roomService.createRoom(roomDto);
    }

    @GetMapping
    public List<RoomVo> findAllRooms(){
        return roomService.findAllRoom();
    }

    @GetMapping("/{memberId}")
    public List<RoomGroup> getAllRoomsByMemberId(@PathVariable("memberId") @Valid @NotNull String memberId) {
        return roomService.getAllRoomsByMemberId(memberId);
    }

    @GetMapping("/room/{roomId}")
    public List<FindAllByRoomIdInterface> findRoomByRoomId(@PathVariable("roomId") @Valid @NotNull String roomId) {
        return roomService.findRoomByRoomId(roomId);
    }
}
