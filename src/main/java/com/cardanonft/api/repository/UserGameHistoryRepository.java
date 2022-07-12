package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserGameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameHistoryRepository extends JpaRepository<UserGameHistory, Integer> {
}
