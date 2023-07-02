package com.example.server.application.controllers;

import com.example.server.domains.member.services.MemberWriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private MemberWriteService memberWriteService;

    @PostMapping("sign-up")
    public void signUp(@RequestBody() MemberDto member) {
        memberWriteService.signUp(member);
    }
}