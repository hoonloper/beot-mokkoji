package com.example.server.domains.chat.service;

import com.example.server.domains.chat.entity.Chat;
import com.example.server.domains.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public Flux<Chat> subscribeMember(String senderId, String receiverId) {
        return chatRepository
                .subscribeMemberWithReceiver(senderId, receiverId)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(new Chat()));
    }

    public Flux<Chat> subscribeRoom(String roomId) {
        return chatRepository
                .subscribeRoom(roomId)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(new Chat()));
    }

    public Chat saveChat(Chat chat) {
        Chat savingChat = Chat.builder()
                .msg(chat.getMsg())
                .senderId(chat.getSenderId())
                .roomId(chat.getRoomId())
                .receiverId(chat.getReceiverId())
                .senderName(chat.getSenderName())
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString())
                .build();
        chatRepository.insert(savingChat).doOnNext(System.out::println).doOnError(System.out::println).subscribe();
        return savingChat;
    }
}
