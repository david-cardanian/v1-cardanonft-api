package com.cardanonft.api.repository;

import com.cardanonft.api.entity.NftWhitelistCandidateEntity;
import com.cardanonft.api.entity.NftWhitelistEntity;
import com.cardanonft.api.entity.enums.WhitelistType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NftWhitelistCandidateRepository extends JpaRepository<NftWhitelistCandidateEntity, Integer>, JpaSpecificationExecutor<NftWhitelistCandidateEntity> {
    List<NftWhitelistCandidateEntity> findTopByStakeAddressAndTypeAndDeleted(String stakeAddress, WhitelistType type, int deleted);
}