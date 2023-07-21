package com.example.server.application.controllers;

import com.example.server.domains.room.dto.RoomDto;
import com.example.server.domains.room.services.RoomService;
import com.example.server.domains.room.vo.RoomVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoomControllerTest {
    @MockBean
    private RoomService roomService;

    private final String END_POINT="/api/v1/rooms";
    @Autowired
    MockMvc mvc;

    @Nested
    @DisplayName("성공 케이스")
    class Success {
        @Test
        @DisplayName("룸 생성 API")
        void createRoomTest() throws Exception {
            String roomId = UUID.randomUUID().toString();
            String memberId = UUID.randomUUID().toString();
            String name = "TEST";
            RoomVo roomVO = RoomVo
                    .builder()
                    .roomId(roomId)
                    .memberId(memberId)
                    .name(name)
                    .build();
            given(roomService.createRoom(any())).willReturn(roomVO);

            RoomDto roomDto = new RoomDto(name, memberId);
            String json = new ObjectMapper().writeValueAsString(roomDto);

            mvc.perform(MockMvcRequestBuilders.post(END_POINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.memberId").value(memberId))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.roomId").value(roomId));
        }

        @Test
        @DisplayName("멤버가 가진 전체 채팅방 가져오기 API")
        void getAllRoomsByMemberId() throws Exception {

            String memberId = "UUID1";
            mvc.perform(MockMvcRequestBuilders.get(END_POINT + "/" + memberId)
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }
}
