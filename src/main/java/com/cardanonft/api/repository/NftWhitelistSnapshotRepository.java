package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAccountEntity;
import com.cardanonft.api.entity.NftWhitelistSnapshotEntity;
import com.cardanonft.api.entity.enums.WhitelistSnapshotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftWhitelistSnapshotRepository extends JpaRepository<NftWhitelistSnapshotEntity, Integer> {
    //NftWhitelistSnapshotEntity findTopByStakeAddressAndIsEnabled(String stakeAddress, boolean isEnabled);
    List<NftWhitelistSnapshotEntity> findByStakeAddressAndIsEnabled(String stakeAddress, String isEnabled);
    List<NftWhitelistSnapshotEntity> findByStakeAddressAndIsEnabledAndWhitelistType(String stakeAddress, String isEnabled, WhitelistSnapshotType whitelistSnapshotType);
}
