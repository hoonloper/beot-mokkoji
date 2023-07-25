package com.example.server.application.controllers;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.service.BeotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

        @Test
        @DisplayName("팔로우 API")
        void follow() throws Exception {
            String EXPECTED_FROM_MEMBER_ID = UUID.randomUUID().toString();
            String EXPECTED_TO_MEMBER_ID = UUID.randomUUID().toString();
            LocalDateTime EXPECTED_CREATED_AT = LocalDateTime.now();
            beotDto = new BeotDto(null, EXPECTED_FROM_MEMBER_ID, EXPECTED_TO_MEMBER_ID, EXPECTED_CREATED_AT);
            mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            json = mapper.writeValueAsString(beotDto);

            mvc.perform(MockMvcRequestBuilders.post(END_POINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }
    }
}
