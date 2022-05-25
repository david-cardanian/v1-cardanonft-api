package com.cardanonft.api.repository;

import com.cardanonft.api.entity.CardanoAddressEntity;
import com.cardanonft.api.entity.MapParcelEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MapParcelRepository extends JpaRepository<MapParcelEntity, String>, JpaSpecificationExecutor<MapParcelEntity> {
    MapParcelEntity findTopByMapParcelIdAndUserIdAndIsEnabled(int mapParcelId, String userId, String isEnabled);
    List<MapParcelEntity> findAllByUserIdAndVillageNftIdAndIsEnabled(String userId,int villageNftId, String isEnabled);
    List<MapParcelEntity> findAllByUserIdAndIsEnabled(String userId, String isEnabled);
    List<MapParcelEntity> findAllByUserIdAndVillageIdAndIsEnabled(String userId, String villageId, String isEnabled);
    List<MapParcelEntity> findAllByUserIdAndParcelX3dAndParcelY3dAndIsEnabled(String userId,int parcelX3d,int parcelY3d, String isEnabled);
    @Transactional
    @Modifying
    @Query(value=" UPDATE map_parcel "
            + " SET village_nft_id = 0"
            + " WHERE map_parcel_id = :mapParcelId", nativeQuery = true)
    void undeployVillage( @Param("mapParcelId")int mapParcelId
    ) throws Exception;
    @Transactional
    @Modifying
    @Query(value=" UPDATE map_parcel "
            + " SET user_id = '', user_img_url = '', user_img_url_thumb = ''"
            + " WHERE map_parcel_id = :mapParcelId", nativeQuery = true)
    void undeployUser( @Param("mapParcelId")int mapParcelId
    ) throws Exception;
    @Transactional
    @Modifying
    @Query(value=" UPDATE map_parcel "
            + " SET moon_onoff = :moonOnoff"
            + " WHERE map_parcel_id = :mapParcelId", nativeQuery = true)
    void moonOnoff( @Param("mapParcelId")int mapParcelId,@Param("moonOnoff")String moonOnoff
    ) throws Exception;
}
