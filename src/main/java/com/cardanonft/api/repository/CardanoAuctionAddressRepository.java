package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAuctionAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardanoAuctionAddressRepository extends JpaRepository<CardanoAuctionAddressEntity, Integer>, JpaSpecificationExecutor<CardanoAuctionAddressEntity> {
    CardanoAuctionAddressEntity findTopByIsEnabledAndNftCollectionId(String isEnabled, int nftCollectionId);
}
