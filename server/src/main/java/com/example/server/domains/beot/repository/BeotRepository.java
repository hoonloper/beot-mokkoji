package com.example.server.domains.beot.repository;

import com.example.server.domains.beot.dto.BeotDto;
import com.example.server.domains.beot.entity.Beot;
import com.example.server.domains.beot.interfaces.BeotFollowingsInterface;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;

public interface BeotRepository extends CrudRepository<Beot, Long> {
    List<Beot> findByFromMemberId(String id);
}
