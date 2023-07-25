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

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

            mvc.perform(post(END_POINT + "/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(uuid))
                    .andExpect(jsonPath("$.name").value("이름"))
                    .andExpect(jsonPath("$.nickname").value("닉네임"))
                    .andExpect(jsonPath("$.birthday").value("2022-06-30"));
        }

        @Test
        @DisplayName("로그인 API")
        void signIn() throws Exception {
            given(memberService.signIn(any())).willReturn(returnValue);

            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(uuid))
                    .andExpect(jsonPath("$.name").value("이름"))
                    .andExpect(jsonPath("$.nickname").value("닉네임"))
                    .andExpect(jsonPath("$.birthday").value("2022-06-30"));
            ;
        }

        @Test
        @DisplayName("회원탈퇴 API")
        void resign() throws Exception {
            mvc.perform(delete(END_POINT + "/resign")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print()).andExpect(status().isNoContent());
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
            mvc.perform(post(END_POINT + "/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.statusCode").value(500))
                    .andExpect(jsonPath("$.statusMessage").value("Internal Server Error"));
        }

        @Test
        @DisplayName("로그인 API(Name) - 잘못된 이름 형식")
        void failSignInWithInvalidName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "@!@$$^#&^$#@", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API(Name) - 이름 1글자")
        void failSignInWithShortName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API(Name) - 이름 16글자")
        void failSignInWithLongName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1111111111111111", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }

        @Test
        @DisplayName("로그인 API(Nickname) - 잘못된 닉네임 형식")
        void failSignInWithInvalidNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "@!@$$^#&^$#@", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API(Nickname) - 닉네임 2글자")
        void failSignInWithShortNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "11", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("로그인 API(Nickname) - 닉네임 21글자")
        void failSignInWithLongNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "111111111111111111111", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }


        @Test
        @DisplayName("회원가입 API - body 없음")
        void failSignUpWithNoBody() throws Exception {
            mvc.perform(post(END_POINT + "/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isInternalServerError())
                    .andExpect(jsonPath("$.statusCode").value(500))
                    .andExpect(jsonPath("$.statusMessage").value("Internal Server Error"));
        }

        @Test
        @DisplayName("회원가입 API(Name) - 잘못된 이름 형식")
        void failSignUpWithInvalidName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "@!@$$^#&^$#@", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("회원가입 API(Name) - 이름 1글자")
        void failSignUpWithShortName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("회원가입 API(Name) - 이름 16글자")
        void failSignUpWithLongName() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "1111111111111111", "닉네임", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }

        @Test
        @DisplayName("회원가입 API(Nickname) - 잘못된 닉네임 형식")
        void failSignUpWithInvalidNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "@!@$$^#&^$#@", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("회원가입 API(Nickname) - 닉네임 2글자")
        void failSignUpWithShortNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "11", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
        @Test
        @DisplayName("회원가입 API(Nickname) - 닉네임 21글자")
        void failSignUpWithLongNickname() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "111111111111111111111", LocalDate.now()));
            mvc.perform(post(END_POINT + "/sign-in")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }

        @Test
        @DisplayName("회원가입 API(Birthday) - 미래 생년월일")
        void failSignUpWithFutureBirthday() throws Exception {
            String json = mapper.writeValueAsString(new MemberDto(null, "이름", "닉네임", LocalDate.now().plus(Period.ofDays(1))));
            mvc.perform(post(END_POINT + "/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$.statusMessage").value(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        }
    }
}
