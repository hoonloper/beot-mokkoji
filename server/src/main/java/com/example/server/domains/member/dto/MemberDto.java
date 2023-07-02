package com.example.server.domains.member.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MemberDto(UUID id, String name, String nickname, LocalDate birthday) {}