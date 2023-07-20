package com.example.server.domains.beot.vo;

import com.example.server.domains.member.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BeotFollowingsVo {
    private final Long id;
    private final Member toMember;
    private final LocalDateTime createdAt;
}
