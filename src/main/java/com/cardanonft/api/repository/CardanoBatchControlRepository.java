package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoBatchControlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardanoBatchControlRepository extends JpaRepository<CardanoBatchControlEntity, Integer>, JpaSpecificationExecutor<CardanoBatchControlEntity> {

}