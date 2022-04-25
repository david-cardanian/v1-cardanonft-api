package com.cardanonft.api.repository;

import com.cardanonft.api.entity.NftWhitelistEntity;
import com.cardanonft.api.entity.VillageTypeEntity;
import com.cardanonft.api.entity.enums.WhitelistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NftWhitelistRepository extends JpaRepository<NftWhitelistEntity, Integer>, JpaSpecificationExecutor<NftWhitelistEntity> {
    NftWhitelistEntity findTopByStakeAddressAndTypeInAndDeleted(String stakeAddress, WhitelistType[] type, int deleted);
}