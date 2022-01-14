package com.cardanonft.api.repository;

import com.cardanonft.api.entity.AuthTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity, String>, JpaSpecificationExecutor<AuthTokenEntity> {

}