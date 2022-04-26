package com.cardanonft.api.repository;

import com.cardanonft.api.entity.NftWhitelistCandidateEntity;
import com.cardanonft.api.entity.NftWhitelistEntity;
import com.cardanonft.api.entity.enums.WhitelistType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface NftWhitelistCandidateRepository extends JpaRepository<NftWhitelistCandidateEntity, Integer>, JpaSpecificationExecutor<NftWhitelistCandidateEntity> {
    List<NftWhitelistCandidateEntity> findTopByStakeAddressAndTypeAndDeleted(String stakeAddress, WhitelistType type, int deleted);
    BigDecimal countAllByTypeAndDeleted(WhitelistType type, int deleted);
}