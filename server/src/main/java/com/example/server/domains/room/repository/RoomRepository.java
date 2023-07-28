package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    @Query("{ 'members': { '$elemMatch': { 'member_id': '0ca75e74-c967-45e9-afd3-dd820ed30b02' } } }")
    List<Room> findAllByMemberIdInMembers(String memberId);
}
