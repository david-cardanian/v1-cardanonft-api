package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cardano_nft_collection")
public class CardanoNftCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id", insertable = false, nullable = false)
    private Integer collectionId;

    @Column(name = "collection_name")
    private String collectionName;

    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "policy_name")
    private String policyName;
    @Column(name = "slot")
    private String slot;
    @Column(name = "policy_id")
    private String policyId;
    @Column(name = "nft_price")
    private Integer nftPrice;

    @Column(name = "target_quantity")
    private Integer targetQuantity;

    @Column(name = "mint_count")
    private Integer mintCount;

    @Column(name = "multi_mint_yn")
    private String multiMintYn = "0";

    @Column(name = "gotcha_yn")
    private String gotchaYn = "0";

    @Column(name = "close_yn")
    private String closeYn = "0";

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at" , insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at" , insertable = true, updatable = true)
    private Date updatedAt=new Date();


}
