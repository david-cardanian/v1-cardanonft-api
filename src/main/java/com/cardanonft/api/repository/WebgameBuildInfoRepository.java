package com.cardanonft.api.repository;

import com.cardanonft.api.entity.WebgameBuildInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebgameBuildInfoRepository extends JpaRepository<WebgameBuildInfo, Integer> {
    WebgameBuildInfo findByGameNameAndEnabled(String gameName, boolean enabled);
}
