package com.cardanonft.api.repository;

import com.cardanonft.api.entity.VillageTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VillageTypeRepository extends JpaRepository<VillageTypeEntity, Integer>, JpaSpecificationExecutor<VillageTypeEntity> {

}