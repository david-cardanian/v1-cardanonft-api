package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoNftCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardanoNftCollectionRepository extends JpaRepository<CardanoNftCollectionEntity, Integer>, JpaSpecificationExecutor<CardanoNftCollectionEntity> {
//    CardanoNftCollectionEntity findTopByAddressOrderByCreatedAtDesc(String addressId);
    List<CardanoNftCollectionEntity> findAllByProjectIdOrderByCreatedAtDesc(int projectId);
}
