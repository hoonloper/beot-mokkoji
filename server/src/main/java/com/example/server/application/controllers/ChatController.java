package com.example.server.application.controllers;

import com.example.server.domains.chat.entity.Chat;
import com.example.server.domains.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chats")
@RestController
public class ChatController {
    private final ChatRepository chatRepository;

    //귓속말 할때 사용
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMsg(@PathVariable String sender,
                             @PathVariable String receiver){
        return chatRepository.mFindBySender(sender,receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @GetMapping(value = "/chatrooms/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable String roomId){
        return chatRepository.mFindByRoomNum(roomId).subscribeOn(Schedulers.boundedElastic());
    }


    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Chat> setMsg(@RequestBody Chat chat){
        chat.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString());
        Chat savingChat = new Chat();
        savingChat.setCreatedAt(chat.getCreatedAt());
        savingChat.setMsg(chat.getMsg());
        savingChat.setRoomId(chat.getRoomId());
        savingChat.setReceiverIdx(chat.getReceiverIdx());
        savingChat.setSenderIdx(chat.getSenderIdx());
        savingChat.setSenderName(chat.getSenderName());
        return chatRepository.save(savingChat);
    }
}