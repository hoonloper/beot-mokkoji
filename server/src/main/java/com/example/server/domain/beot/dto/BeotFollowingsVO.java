package com.example.server.domain.beot.dto;

import com.example.server.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BeotFollowingsVO {
    private final Long id;
    private final Member toMember;
    private final LocalDateTime createdAt;
}
