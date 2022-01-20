package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAccountEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CardanoAccountRepository extends JpaRepository<CardanoAccountEntity, Integer>, JpaSpecificationExecutor<CardanoAccountEntity> {
    List<CardanoAccountEntity> findAllByUserIdAndIsEnabledOrderByCreatedAt(String userId, String isEnabled);
    boolean existsCardanoAccountEntityByUserIdAndStakeAddressAndIsEnabled(String userId, String stakeAddress,String isEnabled);
    @Transactional
    @Modifying
    @Query(value=" UPDATE cardano_account "
            + " SET is_enabled = '0'"
            + " WHERE stake_address = :stakeAddress", nativeQuery = true)
    void updateAccoutDisabled( @Param("stake_address")String stakeAddress
    ) throws Exception;
}