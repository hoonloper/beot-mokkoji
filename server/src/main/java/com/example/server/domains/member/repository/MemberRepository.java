package com.example.server.domains.member.repository;

import com.example.server.domains.member.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {
    Member findByNameAndNickname(String name, String nickname);

    @Query(value = "SELECT m.id, m.name, m.nickname, m.birthday, m.deleted_at FROM members m ORDER BY RAND()", nativeQuery = true)
    List<Member> findAllByRandomOrderBy();
}
