package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface RoomRepository extends ReactiveMongoRepository<Room, Long> {
}
