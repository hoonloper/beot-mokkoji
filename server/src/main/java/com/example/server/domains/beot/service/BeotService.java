package com.example.server.domains.beot.service;

import com.example.server.application.exceptions.BadRequestException;
import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
import com.example.server.domains.beot.vo.BeotFollowingsVo;
import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.entity.Member;
import com.example.server.domains.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Member toMember = new Member(beotDto.toMemberId());
        Member fromMember = new Member(beotDto.fromMemberId());
        beotRepository.save(new Beot(null, toMember, fromMember, LocalDateTime.now()));
    }

    public void unfollow(BeotDto beotDto) {
        if(beotRepository.findById(beotDto.id()).isEmpty()) {
            throw new BadRequestException("존재하지 않는 아이디입니다.");
        };
        beotRepository.delete(new Beot(beotDto.id()));
    }
}
