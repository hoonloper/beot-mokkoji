package com.example.server.domain.beot.services;

import com.example.server.domain.beot.dto.BeotFollowingsVO;
import com.example.server.domain.beot.entity.Beot;
import com.example.server.domain.beot.repository.BeotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BeotReadService {
    private final BeotRepository beotRepository;

    public List<BeotFollowingsVO> getFollowingBeots(String id) {
        return toDto(beotRepository.findByFromMemberId(id));
    }

    private List<BeotFollowingsVO> toDto(List<Beot> beots) {
        return beots.stream().map(beot -> new BeotFollowingsVO(beot.getId(), beot.getToMember(), beot.getCreatedAt())).toList();
    }
}