package com.example.server.application.controllers;

import com.example.server.domain.member.dto.MemberDto;
import com.example.server.domain.member.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private MemberService memberService;

    @PostMapping("sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto signIn(@RequestBody @Valid MemberDto member) {
        return memberService.signIn(member);
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto signUp(@RequestBody @Valid MemberDto member) {
        return memberService.signUp(member);
    }
}