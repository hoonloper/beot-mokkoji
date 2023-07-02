package com.example.server.application.controllers;

import com.example.server.domains.member.services.MemberWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private MemberWriteService memberWriteService;

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID signUp(@RequestBody() MemberDto member) {
        System.out.println(member.toString());
        return memberWriteService.signUp(member);
    }
}