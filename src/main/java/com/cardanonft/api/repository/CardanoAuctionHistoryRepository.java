package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAuctionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardanoAuctionHistoryRepository extends JpaRepository<CardanoAuctionHistoryEntity, Integer>, JpaSpecificationExecutor<CardanoAuctionHistoryEntity> {

}