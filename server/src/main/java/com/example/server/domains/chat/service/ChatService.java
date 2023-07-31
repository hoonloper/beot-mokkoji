package com.example.server.domains.chat.service;

import com.example.server.domains.chat.dto.ChatDto;
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

    public Flux<ChatDto> subscribeMember(String senderId, String receiverId) {
        return chatRepository
                .subscribeMemberWithReceiver(senderId, receiverId)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(ChatDto.builder().build()));
    }

    public Flux<ChatDto> subscribeRoom(String roomId) {
        return chatRepository
                .subscribeRoom(roomId)
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> Mono.just(ChatDto.builder().build()));
    }

    public ChatDto saveChat(ChatDto chat) {
        Chat savingChat = Chat.builder()
                .message(chat.message())
                .senderId(chat.senderId())
                .roomId(chat.roomId())
                .receiverId(chat.receiverId())
                .senderName(chat.senderName())
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).toString())
                .build();
        chatRepository.insert(savingChat).doOnNext(System.out::println).doOnError(System.out::println).subscribe();
        return toDto(savingChat);
    }

    private ChatDto toDto(Chat chat) {
        return ChatDto
                .builder()
                .id(chat.getId())
                .message(chat.getMessage())
                .senderId(chat.getSenderId())
                .roomId(chat.getRoomId())
                .senderName(chat.getSenderName())
                .createdAt(chat.getCreatedAt())
                .receiverId(chat.getReceiverId())
                .build();
    }
}
