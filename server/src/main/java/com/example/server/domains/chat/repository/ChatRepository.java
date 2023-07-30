package com.example.server.domains.chat.repository;

import com.example.server.domains.chat.entity.Chat;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat,String> {
    @Tailable
    @Query("{ 'sender_id': ?0, 'receiver_id': ?1 }") //해당 쿼리가 동작하게 됨.
    Flux<Chat> mFindBySender(String sender, String receiver);
    //Flux- 데이터의 흐름,끊기지 않고 데이터를 지속적으로 받겠다는 의미

    @Tailable
    @Query("{ 'room_id': ?0 }")
    Flux<Chat> mFindByRoomNum(String roomId);
}