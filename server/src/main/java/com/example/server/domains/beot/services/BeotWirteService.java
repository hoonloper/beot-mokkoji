package com.example.server.domains.beot.services;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.repository.BeotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BeotWirteService {
    private final BeotRepository beotRepository;

    public void follow(BeotDto beotDto) {
        Beot beot = new Beot();
        beot.setToMemberId(beotDto.toMemberId());
        beot.setFromMemberId(beotDto.fromMemberId());
        beot.setCreatedAt(LocalDateTime.now());
        beotRepository.save(beot);
    }
}
