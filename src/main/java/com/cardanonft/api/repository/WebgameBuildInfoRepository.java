package com.cardanonft.api.repository;

import com.cardanonft.api.entity.WebgameBuildInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WebgameBuildInfoRepository extends JpaRepository<WebgameBuildInfo, Integer> {
    WebgameBuildInfo findByGameNameAndEnabled(String gameName, boolean enabled);

    @Query(value = "select * from webgame_build_info where enabled = true " +
            "order by rand() limit 1", nativeQuery = true)
    WebgameBuildInfo findRandomOneGame();
}
