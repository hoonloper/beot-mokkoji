package com.example.server.application.controllers;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public MemberDto signIn(@RequestBody @Valid MemberDto member) {
        return memberService.signIn(member);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto signUp(@RequestBody @Valid MemberDto member) {
        return memberService.signUp(member);
    }

    @DeleteMapping("/resign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resign(@RequestBody @Valid MemberDto member) {
        memberService.resign(member);
    }
}