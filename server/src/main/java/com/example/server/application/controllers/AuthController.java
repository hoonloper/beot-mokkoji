package com.example.server.application.controllers;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.services.MemberReadService;
import com.example.server.domains.member.services.MemberWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private MemberWriteService memberWriteService;
    @Autowired
    private MemberReadService memberReadService;

    @PostMapping("sign-in")
    public MemberDto signIn(@RequestBody() MemberDto member) {
        return memberReadService.signIn(member);
    }

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody() MemberDto member) {
        System.out.println(member.toString());
        return memberWriteService.signUp(member);
    }
}