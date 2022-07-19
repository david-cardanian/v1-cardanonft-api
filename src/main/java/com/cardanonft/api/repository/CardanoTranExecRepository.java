package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoTranExecEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardanoTranExecRepository extends JpaRepository<CardanoTranExecEntity, Integer>, JpaSpecificationExecutor<CardanoTranExecEntity> {
    List<CardanoTranExecEntity> findAllByToStakeAddressAndCollectionIdAndIsEnabled(String stakeAddress, int collectionId, String isEnabled);
}