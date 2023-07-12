package com.example.server.application.controllers;

import com.example.server.domains.chat.interfaces.ChatsInterface;
import com.example.server.domains.chat.services.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("{roomId}")
    public List<ChatsInterface> findAllByRoomId(@PathVariable("roomId") String roomId) {
        return chatService.findAllByRoomId(roomId);
    }
}