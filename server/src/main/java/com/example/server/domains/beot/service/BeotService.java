package com.example.server.domains.beot.service;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
import com.example.server.domains.beot.vo.BeotFollowingsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BeotService {
    private final BeotRepository beotRepository;

    public List<BeotFollowingsVo> getFollowingBeots(String id) {
        return toVoList(beotRepository.findByFromMemberId(id));
    }

    private List<BeotFollowingsVo> toVoList(List<Beot> beots) {
        return beots.stream().map(beot -> BeotFollowingsVo
                .builder()
                .id(beot.getId())
                .toMember(beot.getToMember())
                .createdAt(beot.getCreatedAt())
                .build()).toList();
    }

    public void follow(BeotDto beotDto) {
        Beot beot = new Beot();
        beot.setToMemberId(beotDto.toMemberId());
        beot.setFromMemberId(beotDto.fromMemberId());
        beot.setCreatedAt(LocalDateTime.now());
        beotRepository.save(beot);
    }
}
