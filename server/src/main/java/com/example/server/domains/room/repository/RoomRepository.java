package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;


public interface RoomRepository extends ReactiveMongoRepository<Room, Long> {
    @Tailable
    @Query("{ 'sender_idx': ?0, 'receiver_idx': ?1 }") //해당 쿼리가 동작하게 됨.
    Flux<Room> findAllByMemberId(String sender, String receiver);
}
