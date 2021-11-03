package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardanoAuctionRepository extends JpaRepository<CardanoAuctionEntity, Integer>, JpaSpecificationExecutor<CardanoAuctionEntity> {
    CardanoAuctionEntity findTopByIsEnabledAndProjectIdOrderByStartDate(String isEnabled, int projectId);
}
