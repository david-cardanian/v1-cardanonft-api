package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CardanoAddressRepository extends JpaRepository<CardanoAddressEntity, String>, JpaSpecificationExecutor<CardanoAddressEntity> {
    List<CardanoAddressEntity> findAllByIsEnabledOrderByCreatedAtDesc(String isEnabled);
    CardanoAddressEntity findTopByAddressIdAndIsEnabledOrderByCreatedAtDesc(String addressId, String isEnabled);
    CardanoAddressEntity findTopByAddressIdOrderByCreatedAtDesc(String addressId);
}
