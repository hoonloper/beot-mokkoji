package com.example.server.domains.beot.service;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
import com.example.server.domains.beot.vo.BeotFollowingsVo;
import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeotService {
    private final BeotRepository beotRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BeotService(BeotRepository beotRepository, MemberRepository memberRepository) {
        this.beotRepository = beotRepository;
        this.memberRepository = memberRepository;
    }

    public List<MemberDto> getRandomBeots() {
        return memberRepository
                .findAllByRandomOrderBy()
                .stream()
                .map(MemberDto::toDto)
                .toList();
    }

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
        beotRepository.save(new Beot(beotDto.fromMemberId(), beotDto.toMemberId(), beotDto.createdAt()));
    }

    public void unfollow(BeotDto beotDto) {
        beotRepository.delete(new Beot(beotDto.id(), beotDto.fromMemberId(), beotDto.toMemberId()));
    }
}
