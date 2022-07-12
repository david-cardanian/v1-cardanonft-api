package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserTokenHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface UserTokenHistoryRepository extends JpaRepository<UserTokenHistory, Integer> {
}
