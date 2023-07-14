package com.example.server.application.controllers;

import com.example.server.domain.room.service.RoomService;
import com.example.server.domain.room.vos.RoomVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @MockBean
    private RoomService roomService;

    private final String END_POINT="/api/v1/rooms";
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("룸 생성 테스트")
    void createRoomTest() throws Exception {
        String roomId = UUID.randomUUID().toString();
        String memberId = UUID.randomUUID().toString();
        RoomVO roomVO = RoomVO
                .builder()
                .roomId(roomId)
                .memberId(memberId)
                .name("TEST")
                .build();
        String json = new ObjectMapper().writeValueAsString(roomVO);
        mvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isCreated());
    }
}
