package com.example.server.application.controllers;

import com.example.server.domains.chat.interfaces.ChatsInterface;
import com.example.server.domains.chat.service.ChatService;
import com.example.server.domains.chat.vo.ChatEventType;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ChatControllerTest {
    @MockBean
    private ChatService chatService;

    @Autowired
    MockMvc mvc;

    private final String END_POINT = "/api/v1/chats";


    @Nested
    @DisplayName("성공 케이스")
    class Success {
        @Test
        @DisplayName("채팅 목록 조회 API")
        void findAllByRoomId() throws Exception {
            List<ChatsInterface> chatsList = new ArrayList<>();
            for (long i = 1; i <= 5; i++) {
                chatsList.add(new Chats(i, UUID.randomUUID().toString(), "메시지" + i, ChatEventType.MESSAGE, LocalDateTime.now()));
            }
            given(chatService.findAllByRoomId(any())).willReturn(chatsList);

            String roomId = UUID.randomUUID().toString();
            mvc.perform(MockMvcRequestBuilders.get(END_POINT + "/" + roomId)
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(jsonPath("$[0].id", chatsList.get(0).getId()).exists())
                    .andExpect(jsonPath("$[0].senderId", chatsList.get(0).getSenderId()).exists())
                    .andExpect(jsonPath("$[0].message", chatsList.get(0).getMessage()).exists())
                    .andExpect(jsonPath("$[0].type", chatsList.get(0).getType()).exists())
                    .andExpect(jsonPath("$[0].sendAt", chatsList.get(0).getSendAt()).exists())
                    .andExpect(jsonPath("$[1].id", chatsList.get(1).getId()).exists())
                    .andExpect(jsonPath("$[1].senderId", chatsList.get(1).getSenderId()).exists())
                    .andExpect(jsonPath("$[1].message", chatsList.get(1).getMessage()).exists())
                    .andExpect(jsonPath("$[1].type", chatsList.get(1).getType()).exists())
                    .andExpect(jsonPath("$[1].sendAt", chatsList.get(1).getSendAt()).exists())
                    .andExpect(jsonPath("$[2].id", chatsList.get(2).getId()).exists())
                    .andExpect(jsonPath("$[2].senderId", chatsList.get(2).getSenderId()).exists())
                    .andExpect(jsonPath("$[2].message", chatsList.get(2).getMessage()).exists())
                    .andExpect(jsonPath("$[2].type", chatsList.get(2).getType()).exists())
                    .andExpect(jsonPath("$[2].sendAt", chatsList.get(2).getSendAt()).exists())
                    .andExpect(jsonPath("$[3].id", chatsList.get(3).getId()).exists())
                    .andExpect(jsonPath("$[3].senderId", chatsList.get(3).getSenderId()).exists())
                    .andExpect(jsonPath("$[3].message", chatsList.get(3).getMessage()).exists())
                    .andExpect(jsonPath("$[3].type", chatsList.get(3).getType()).exists())
                    .andExpect(jsonPath("$[3].sendAt", chatsList.get(3).getSendAt()).exists())
                    .andExpect(jsonPath("$[4].id", chatsList.get(4).getId()).exists())
                    .andExpect(jsonPath("$[4].senderId", chatsList.get(4).getSenderId()).exists())
                    .andExpect(jsonPath("$[4].message", chatsList.get(4).getMessage()).exists())
                    .andExpect(jsonPath("$[4].type", chatsList.get(4).getType()).exists())
                    .andExpect(jsonPath("$[4].sendAt", chatsList.get(4).getSendAt()).exists());
        }
    }
}


class Chats implements ChatsInterface {
    Long id;
    String senderId;
    String message;
    ChatEventType type;
    LocalDateTime sendAt;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getSenderId() {
        return senderId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public ChatEventType getType() {
        return type;
    }

    @Override
    public LocalDateTime getSendAt() {
        return sendAt;
    }

    public Chats(Long id, String senderId, String message, ChatEventType type, LocalDateTime sendAt) {
        this.id = id;
        this.senderId = senderId;
        this.message = message;
        this.type = type;
        this.sendAt = sendAt;
    }
}