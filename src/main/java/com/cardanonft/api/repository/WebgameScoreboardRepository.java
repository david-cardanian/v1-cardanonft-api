package com.cardanonft.api.repository;

import com.cardanonft.api.entity.WebgameScoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebgameScoreboardRepository extends JpaRepository<WebgameScoreboard, Integer> {
    List<WebgameScoreboard> findTop7ByGameIdAndEnabledOrderByScoreDescCreatedAtDesc(int gameId, boolean enabled);

}
