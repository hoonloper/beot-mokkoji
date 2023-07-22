package com.example.server.application.controllers;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.service.MemberService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
        String uuid;
        ObjectMapper mapper;
        MemberDto memberDto;
        String json;
        MemberDto returnValue;

        @BeforeEach
        void init() throws JsonProcessingException {
            String EXPECTED_NAME = "이름";
            String EXPECTED_NICKNAME = "닉네임";
            LocalDate EXPECTED_BIRTHDAY = LocalDate.of(2022, Month.JUNE, 30);
            memberDto = new MemberDto(null, EXPECTED_NAME, EXPECTED_NICKNAME, EXPECTED_BIRTHDAY);
            uuid = UUID.randomUUID().toString();
            mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            json = mapper.writeValueAsString(memberDto);
            returnValue = new MemberDto(uuid, EXPECTED_NAME, EXPECTED_NICKNAME, EXPECTED_BIRTHDAY);
        }

        @Test
        @DisplayName("회원가입 API")
        void signUp() throws Exception {
            given(memberService.signUp(any())).willReturn(returnValue);

            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("이름"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("닉네임"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2022-06-30"));
        }

        @Test
        @DisplayName("로그인 API")
        void signIn() throws Exception {
            given(memberService.signIn(any())).willReturn(returnValue);

            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(uuid))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("이름"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("닉네임"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("2022-06-30"));
            ;
        }

        @Test
        @DisplayName("회원탈퇴 API")
        void resign() throws Exception {
            mvc.perform(MockMvcRequestBuilders.delete(END_POINT + "/resign")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print()).andExpect(MockMvcResultMatchers.status().isNoContent());
        }
    }


    @Nested
    @DisplayName("실패 케이스")
    class Fail {
        ObjectMapper mapper;
        @BeforeEach
        void init() {
            mapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        @Test
        @DisplayName("로그인 API - body 없음")
        void failSignInWithNoBody() throws Exception {
            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(500))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value("Internal Server Error"));
        }

        @Test
        @DisplayName("로그인 API - 잘못된 이름 형식")
        void failSignInWithInvalidName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "@!@$$^#&^$#@", "닉네임", LocalDate.now()));
            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API - 이름 1글자")
        void failSignInWithShortName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1", "닉네임", LocalDate.now()));
            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API - 이름 16글자")
        void failSignInWithLongName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1111111111111111", "닉네임", LocalDate.now()));
            mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
    }
}
