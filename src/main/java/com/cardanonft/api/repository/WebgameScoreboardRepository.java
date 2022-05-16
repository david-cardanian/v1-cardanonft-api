package com.cardanonft.api.repository;

import com.cardanonft.api.entity.WebgameScoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebgameScoreboardRepository extends JpaRepository<WebgameScoreboard, Integer> {


}
