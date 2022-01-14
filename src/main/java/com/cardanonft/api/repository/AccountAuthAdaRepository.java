package com.cardanonft.api.repository;

import com.cardanonft.api.entity.AccountAuthAdaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountAuthAdaRepository extends JpaRepository<AccountAuthAdaEntity, Integer>, JpaSpecificationExecutor<AccountAuthAdaEntity> {

}