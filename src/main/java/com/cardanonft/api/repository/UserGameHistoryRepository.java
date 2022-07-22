package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserGameHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameHistoryRepository extends JpaRepository<UserGameHistory, Integer> {

    List<UserGameHistory> findAllByRoomNameAndTeamAndIsEnabled(String roomId, String team, String isEnabled);

    Page<UserGameHistory> findByUserIdAndIsEnabled(String userId, String isEnabled, Pageable pageable);

    List<UserGameHistory> findAllByRoomNameAndIsEnabled(String roomId,String isEnabled);
}
