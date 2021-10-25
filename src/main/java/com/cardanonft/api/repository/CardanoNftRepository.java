package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoNftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardanoNftRepository extends JpaRepository<CardanoNftEntity, Integer>, JpaSpecificationExecutor<CardanoNftEntity> {
    List<CardanoNftEntity> findAllByIsEnabledOrderByCreatedAtDesc(String isEnabled);
    CardanoNftEntity findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(int nftId, String isEnabled);
//    CardanoNftEntity findTopByCollectionIdAndIsEnabledOrderByCreatedAtDesc(int collectionId, String isEnabled);
//    CardanoNftEntity findTopByNftIdOrderByCreatedAtDesc(int nftId);
//    List<CardanoNftEntity> findAllByCollectionIdAndIsEnabledOrderByCreatedAtDesc(int collectionId,String isEnabled);
}
