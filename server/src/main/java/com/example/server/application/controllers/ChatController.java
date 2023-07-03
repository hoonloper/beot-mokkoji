package com.example.server.application.controllers;

import com.example.server.domains.chat.services.ChatRoom;
import com.example.server.domains.chat.services.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRoom();
    }
}