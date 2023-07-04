package com.example.server.domains.room.service;

import com.example.server.domains.room.repository.RoomRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Data
@Service
public class RoomService {
    private final RoomRepository roomRepository;
}
