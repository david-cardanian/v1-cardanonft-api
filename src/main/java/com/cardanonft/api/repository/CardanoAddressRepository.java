package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardanoAddressRepository extends JpaRepository<CardanoAddressEntity, String>, JpaSpecificationExecutor<CardanoAddressEntity> {
    List<CardanoAddressEntity> findAllByIsEnabledOrderByCreatedAtDesc(String isEnabled);
    CardanoAddressEntity findTopByAddressIdAndIsEnabledOrderByCreatedAtDesc(String addressId, String isEnabled);
    CardanoAddressEntity findTopByAddressIdOrderByCreatedAtDesc(String addressId);
    @Query(value = "SELECT * " +
            "FROM cardano_address " +
            "WHERE auth_type = 2 and is_enabled = '1' " +
            "ORDER BY rand() limit 1", nativeQuery = true)
    CardanoAddressEntity findRandomOneAddress();
}
