package com.example.server.application.controllers;

import com.example.server.domain.member.dto.MemberDto;
import com.example.server.domain.member.services.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthControllerTest.class)
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
        @DisplayName("로그인 API")
        void signIn() throws Exception {
            // given
            MemberDto memberDto = new MemberDto(null, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30));
            String json = new ObjectMapper().writeValueAsString(memberDto.getClass());

            // when
            ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(END_POINT + "/sign-up")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON)
            );

            // then
            resultActions.andDo(print()).andExpect(status().isCreated());
        }
    }
}
