package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserTokenHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenHistoryRepository extends JpaRepository<UserTokenHistory, Integer> {
}
