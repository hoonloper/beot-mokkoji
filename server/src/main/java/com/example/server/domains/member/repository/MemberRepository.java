package com.example.server.domains.member.repository;

import com.example.server.domains.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {}
