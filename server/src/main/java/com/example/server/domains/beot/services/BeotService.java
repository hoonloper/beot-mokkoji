package com.example.server.domains.beot.services;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
import com.example.server.domains.beot.vo.BeotFollowingsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BeotService {
    private final BeotRepository beotRepository;

    public List<BeotFollowingsVO> getFollowingBeots(String id) {
        return toDto(beotRepository.findByFromMemberId(id));
    }

    private List<BeotFollowingsVO> toDto(List<Beot> beots) {
        return beots.stream().map(beot -> new BeotFollowingsVO(beot.getId(), beot.getToMember(), beot.getCreatedAt())).toList();
    }

    public void follow(BeotDto beotDto) {
        Beot beot = new Beot();
        beot.setToMemberId(beotDto.toMemberId());
        beot.setFromMemberId(beotDto.fromMemberId());
        beot.setCreatedAt(LocalDateTime.now());
        beotRepository.save(beot);
    }
}
