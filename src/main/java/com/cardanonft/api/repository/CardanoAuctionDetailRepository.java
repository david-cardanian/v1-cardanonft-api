package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAuctionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardanoAuctionDetailRepository extends JpaRepository<CardanoAuctionDetailEntity, Integer>, JpaSpecificationExecutor<CardanoAuctionDetailEntity> {
    CardanoAuctionDetailEntity findTopByIsEnabledAndAuctionDetailId(String isEnabled, int auctionDetailId);
}
