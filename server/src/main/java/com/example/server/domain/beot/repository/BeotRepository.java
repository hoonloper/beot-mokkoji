package com.example.server.domain.beot.repository;

import com.example.server.domain.beot.entity.Beot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeotRepository extends CrudRepository<Beot, Long> {
    List<Beot> findByFromMemberId(String id);
}
