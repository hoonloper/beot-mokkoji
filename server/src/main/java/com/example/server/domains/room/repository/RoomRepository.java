package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(basePackageClasses = RoomRepository.class)
public interface RoomRepository extends MongoRepository<Room, String> {
    @Query("{ 'members': { $in: ?0 } }")
    List<Room> findAllByMemberId(String memberId);
}
