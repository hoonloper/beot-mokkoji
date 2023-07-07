package com.example.server.domains.chat.repository;

import com.example.server.domains.chat.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
