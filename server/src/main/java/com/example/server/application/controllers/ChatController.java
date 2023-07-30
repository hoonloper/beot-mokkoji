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

import java.time.Duration;
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
    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver){
        return chatRepository
                .mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(new Chat()));
    }

    @CrossOrigin
    @GetMapping(value = "/chatrooms/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable String roomId){
        System.out.println(roomId + "컨트롤러");
        Flux<Chat> chatFlux = chatRepository.mFindByRoomNum(roomId)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(new Chat()));

        System.out.println(chatFlux);
        return chatFlux;
    }


    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chat setMsg(@RequestBody Chat chat){
        Chat savingChat = Chat.builder()
                .msg(chat.getMsg())
                .senderId(chat.getSenderId())
                .roomId(chat.getRoomId())
                .receiverId(chat.getReceiverId())
                .senderName(chat.getSenderName())
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString())
                .build();
        chatRepository.save(savingChat).doOnNext(System.out::println).doOnError(System.out::println).subscribe();
        return savingChat;
    }
}