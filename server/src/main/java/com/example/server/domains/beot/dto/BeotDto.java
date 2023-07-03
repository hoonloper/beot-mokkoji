package com.example.server.domains.beot.dto;

import java.time.LocalDateTime;

public record BeotDto(Long id, String fromMemberId, String toMemberId, LocalDateTime createdAt) {
}
