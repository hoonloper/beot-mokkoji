package com.example.server.domains.member.dto;

import java.time.LocalDate;

public record MemberDto(String id, String name, String nickname, LocalDate birthday) {}