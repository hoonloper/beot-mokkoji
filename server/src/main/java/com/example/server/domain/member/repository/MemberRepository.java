package com.example.server.domain.member.repository;

import com.example.server.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
    Member findByNameAndNickname(String name, String nickname);
}
