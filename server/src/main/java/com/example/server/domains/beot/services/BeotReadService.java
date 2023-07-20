package com.example.server.domains.beot.services;

import com.example.server.domains.beot.vo.BeotFollowingsVO;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
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
