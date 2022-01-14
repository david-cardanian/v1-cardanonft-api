package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoTranExecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardanoTranExecRepository extends JpaRepository<CardanoTranExecEntity, Integer>, JpaSpecificationExecutor<CardanoTranExecEntity> {

}