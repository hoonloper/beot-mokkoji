package com.example.server.domain.chat.repository;

import com.example.server.domain.chat.entity.Chat;
import com.example.server.domain.chat.interfaces.ChatsInterface;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<ChatsInterface> findAllByRoomId(String roomId);
}
