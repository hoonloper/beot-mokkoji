package com.example.server.application.controllers;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private MemberService memberService;

    @PostMapping("sign-in")
    public MemberDto signIn(@RequestBody() MemberDto member) {
        return memberService.signIn(member);
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody() MemberDto member) {
        return memberService.signUp(member);
    }
}