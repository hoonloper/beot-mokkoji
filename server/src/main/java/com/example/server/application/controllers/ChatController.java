package com.example.server.application.controllers;

import com.example.server.domains.chat.dto.ChatDto;
import com.example.server.domains.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
@RestController
public class ChatController {
    private final ChatService chatService;

    //귓속말 할때 사용
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatDto> subscribeMember(@PathVariable String sender, @PathVariable String receiver){
        return chatService.subscribeMember(sender, receiver);
    }

    @CrossOrigin
    @GetMapping(value = "/chatrooms/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatDto> findByRoomNum(@PathVariable String roomId){
        return chatService.subscribeRoom(roomId);
    }


    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatDto setMsg(@RequestBody ChatDto chat){
        return chatService.saveChat(chat);
    }
}