package com.example.server.application.controllers;

import com.example.server.domain.room.service.RoomService;
import com.example.server.domain.room.vos.RoomVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @MockBean
    private RoomService roomService;

    private final String END_POINT="/api/v1/rooms";
    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("룸 생성 성공 테스트")
    void createRoomTest() throws Exception {
        // given
        String roomId = UUID.randomUUID().toString();
        String memberId = UUID.randomUUID().toString();
        RoomVO roomVO = RoomVO
                .builder()
                .roomId(roomId)
                .memberId(memberId)
                .name("TEST")
                .build();
        String json = new ObjectMapper().writeValueAsString(roomVO);

        try {
            // when
            ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            );

            // then
            resultActions.andDo(print()).andExpect(status().isCreated());
        } catch (Exception e) {
            fail("Failed: createRoom");
        }
    }

    @Test
    @DisplayName("멤버가 가지고 있는 전체 채팅방 가져오기")
    void getAllRoomsByMemberId() throws Exception {

        String memberId = "UUID1";
        mvc.perform(MockMvcRequestBuilders.get(END_POINT + "/" + memberId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }
}
