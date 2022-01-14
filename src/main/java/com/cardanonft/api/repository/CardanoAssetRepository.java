package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAssetEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CardanoAssetRepository extends JpaRepository<CardanoAssetEntity, Integer>, JpaSpecificationExecutor<CardanoAssetEntity> {
    CardanoAssetEntity findTopByAssetIdAndIsEnabled(int assetId, String isEnbled);
    @Transactional
    @Modifying
    @Query(value=" UPDATE cardano_asset "
            + " SET is_enabled = '0'"
            + " WHERE stake_address = :stakeAddress", nativeQuery = true)
    void updateAssetDisabledAll( @Param("stakeAddress")String stakeAddress
    ) throws Exception;
    @Transactional
    @Modifying
    @Query(value=" UPDATE cardano_asset "
            + " SET is_enabled = '0'"
            + " WHERE asset_id = :assetId", nativeQuery = true)
    void updateAssetDisabled( @Param("assetId")int assetId
    ) throws Exception;
    List<CardanoAssetEntity> findAllByStakeAddressAndIsEnabled(String stakeAddress, String isEnbled);
}