package com.example.server.domains.room.repository;

import com.example.server.domains.room.entity.Room;
import com.example.server.domains.room.interfaces.FindAllByMemberIdInterface;
import com.example.server.domains.room.interfaces.FindAllByRoomIdInterface;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    // memberId에 해당하는 유저만 제외하고 모든 채팅방 인원 불러오기
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "SELECT rm.id as id, rm.room_id as roomId, rm.member_id as memberId, rm.name as name, m.id as memberId, m.name as memberName, m.nickname as memberNickname FROM ROOMS rm " +
            "INNER JOIN members m ON rm.member_id = m.id " +
            "WHERE room_id IN (SELECT room_id FROM ROOMS WHERE member_id != :memberId) AND member_id != :memberId")
    List<FindAllByMemberIdInterface> findAllByMemberId(@Param("memberId") String memberId);

    List<FindAllByRoomIdInterface> findAllByRoomId(String roomId);
}
