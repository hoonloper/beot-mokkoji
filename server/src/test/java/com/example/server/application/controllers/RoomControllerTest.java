package com.example.server.application.controllers;

import com.example.server.domains.room.dto.RoomDto;
import com.example.server.domains.room.service.RoomService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

            mvc.perform(post(END_POINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.memberId").value(memberId))
            .andExpect(jsonPath("$.name").value(name))
            .andExpect(jsonPath("$.roomId").value(roomId));
        }

        @Test
        @DisplayName("멤버가 가진 전체 채팅방 가져오기 API")
        void getAllRoomsByMemberId() throws Exception {
            String roomId = UUID.randomUUID().toString();
            String name = "이름1";
            Long id = 1L;
            String memberId = UUID.randomUUID().toString();
            String memberName = "멤버이름1";
            String memberNickname = "멤버닉네임1";
            String roomId2 = UUID.randomUUID().toString();
            String name2 = "이름2";
            Long id2 = 2L;
            String memberId2 = UUID.randomUUID().toString();
            String memberName2 = "멤버이름2";
            String memberNickname2 = "멤버닉네임2";

            List<RoomMember> roomMembers = new ArrayList<>();
            roomMembers.add(new RoomMember(id, memberId, memberName, memberNickname));
            roomMembers.add(new RoomMember(id2, memberId2, memberName2, memberNickname2));
            List<RoomMember> roomMembers2 = new ArrayList<>();
            roomMembers2.add(new RoomMember(id2, memberId2, memberName2, memberNickname2));
            roomMembers2.add(new RoomMember(id, memberId, memberName, memberNickname));

            RoomGroup roomGroup = new RoomGroup(roomId, name);
            roomGroup.setRoomMembers(roomMembers);
            RoomGroup roomGroup2 = new RoomGroup(roomId2, name2);
            roomGroup.setRoomMembers(roomMembers2);

            List<RoomGroup> roomGroupList = new ArrayList<>();
            roomGroupList.add(roomGroup);
            roomGroupList.add(roomGroup2);


            given(roomService.getAllRoomsByMemberId(any())).willReturn(roomGroupList);

            String expectedRoomId = "$.[?(@.roomId == '%s')]";
            String expectedMemberId = "$.[?(@.name == '%s')]";
            String expectedRoomMemberId = "$..roomMembers[?(@.id == '%d')]";
            String expectedRoomMemberName = "$..roomMembers[?(@.memberName == '%s')]";
            String expectedRoomMemberNickname = "$..roomMembers[?(@.memberNickname == '%s')]";
            String expectedRoomMemberMemberId = "$..roomMembers[?(@.memberId == '%s')]";

            mvc.perform(get(END_POINT + "/" + name)
                            .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath(expectedRoomId, roomId).exists())
                    .andExpect(jsonPath(expectedRoomId, roomId2).exists())
                    .andExpect(jsonPath(expectedMemberId, name).exists())
                    .andExpect(jsonPath(expectedMemberId, name2).exists())
                    .andExpect(jsonPath(expectedRoomMemberId, id).exists())
                    .andExpect(jsonPath(expectedRoomMemberName, memberName).exists())
                    .andExpect(jsonPath(expectedRoomMemberNickname, memberNickname).exists())
                    .andExpect(jsonPath(expectedRoomMemberMemberId, memberId).exists())
                    .andExpect(jsonPath(expectedRoomMemberId, id2).exists())
                    .andExpect(jsonPath(expectedRoomMemberName, memberName2).exists())
                    .andExpect(jsonPath(expectedRoomMemberNickname, memberNickname2).exists())
                    .andExpect(jsonPath(expectedRoomMemberMemberId, memberId2).exists())
                    .andExpect(jsonPath("$..['roomId']").exists())
                    .andExpect(jsonPath("$..['name']").exists())
                    .andExpect(jsonPath("$[0]").exists())
                    .andExpect(jsonPath("$[1]").exists());

        }
    }
}
