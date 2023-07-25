package com.example.server.application.controllers;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.service.BeotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BeotControllerTest {
    @MockBean
    private BeotService beotService;

    private final String END_POINT="/api/v1/beots";

    @Autowired
    MockMvc mvc;

    @Nested
    @DisplayName("성공 케이스")
    class Success {
        BeotDto beotDto;
        ObjectMapper mapper;
        String json;
        String EXPECTED_FROM_MEMBER_ID;
        String EXPECTED_TO_MEMBER_ID;
        LocalDateTime EXPECTED_CREATED_AT;
        @BeforeEach
        void init() throws JsonProcessingException {
            EXPECTED_FROM_MEMBER_ID = UUID.randomUUID().toString();
            EXPECTED_TO_MEMBER_ID = UUID.randomUUID().toString();
            EXPECTED_CREATED_AT = LocalDateTime.now();
            mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }

        @Test
        @DisplayName("팔로우 API")
        void follow() throws Exception {
            beotDto = new BeotDto(null, EXPECTED_FROM_MEMBER_ID, EXPECTED_TO_MEMBER_ID, EXPECTED_CREATED_AT);
            json = mapper.writeValueAsString(beotDto);

            mvc.perform(post(END_POINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("언팔로우 API")
        void unfollow() throws Exception {
            beotDto = new BeotDto(12L, EXPECTED_FROM_MEMBER_ID, EXPECTED_TO_MEMBER_ID, EXPECTED_CREATED_AT);
            json = mapper.writeValueAsString(beotDto);

            mvc.perform(delete(END_POINT + "/following")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isNoContent());
        }
    }
}
