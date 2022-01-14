package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cardano_asset")
public class CardanoAssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "asset_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assetId;

    @Column(name = "asset_hash", nullable = false)
    private String assetHash;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "stake_address")
    private String stakeAddress;

    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "fingerprint")
    private String fingerprint;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "initial_mint_tx_hash")
    private String initialMintTxHash;

    @Column(name = "mint_or_burn_count")
    private Integer mintOrBurnCount;

    @Column(name = "continent_id")
    private String continentId;

    @Column(name = "village_id")
    private String villageId;

    @Column(name = "village_number")
    private Integer villageNumber;

    @Column(name = "parcel_x")
    private Integer parcelX;

    @Column(name = "parcel_y")
    private Integer parcelY;

    @Column(name = "batch_exec_yn")
    private String batchExecYn;

    @Column(name = "is_enabled")
    private String isEnabled = "1";

    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

    @Column(name = "onchain_metadata")
    private String onchainMetadata;

    @Column(name = "metadata")
    private String metadata;

}
