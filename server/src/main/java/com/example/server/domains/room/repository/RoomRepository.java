package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
