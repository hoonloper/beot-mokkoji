package com.example.server.application.controllers;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.service.MemberService;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthControllerTest {
    @MockBean
    private MemberService memberService;

    @Autowired
    MockMvc mvc;

    private final String END_POINT = "/api/v1/auth";

    @Nested
    @DisplayName("성공 케이스")
    class Success {
        @Test
        @DisplayName("회원가입 API")
        void signUp() throws Exception {
            String uuid = UUID.randomUUID().toString();
            given(memberService.signUp(any())).willReturn(new MemberDto(uuid, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30)));

            MemberDto memberDto = new MemberDto(null, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30));
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String json = mapper.writeValueAsString(memberDto);

            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print()).andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("이름"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("닉네임"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2022-06-30"));
        }
        @Test
        @DisplayName("로그인 API")
        void signIn() throws Exception {
            String uuid = UUID.randomUUID().toString();
            given(memberService.signIn(any())).willReturn(new MemberDto(uuid, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30)));

            MemberDto memberDto = new MemberDto(null, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30));
            ObjectMapper mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String json = mapper.writeValueAsString(memberDto);

            ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("이름"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("닉네임"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2022-06-30"));;
        }
    }
}
