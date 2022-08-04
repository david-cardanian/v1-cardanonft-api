package com.cardanonft.api.entity;

import com.cardanonft.api.entity.enums.WhitelistSnapshotType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "nft_whitelist_snapshot")
@Getter
@Setter
@NoArgsConstructor
public class NftWhitelistSnapshotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "snapshot_id", nullable = false)
    private Integer snapshotId;

    @Enumerated(EnumType.STRING)
    @Column(name = "whitelist_type")
    private WhitelistSnapshotType whitelistType;

    @Column(name = "asset_hash")
    private String assetHash;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "stake_address")
    private String stakeAddress;

    @Column(name = "address_id")
    private String addressId;

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
    private String mintOrBurnCount;

    @Column(name = "onchain_metadata")
    private String onchainMetadata;

    @Column(name = "metadata")
    private String metadata;

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

    @Column(name = "is_enabled")
    private String isEnabled;

    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

}
