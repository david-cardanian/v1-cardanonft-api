package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cardano_nft")
@Data
public class CardanoNftEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nft_id", insertable = false, nullable = false)
    private Integer nftId;

    @Column(name = "collection_id")
    private Integer collectionId;

    @Column(name = "nft_name")
    private String nftName;

    @Column(name = "age")
    private String age;
    @Column(name = "nft_name_kor")
    private String nftNameKor;

    @Column(name = "token_name")
    private String tokenName;

    @Column(name = "description")
    private String description;

    @Column(name = "meta_str")
    private String metaStr;

    @Column(name = "nft_price")
    private Integer nftPrice;

    @Column(name = "mint_count")
    private Integer mintCount;

    @Column(name = "gotcha_min_count")
    private Integer gotchaMinCount;

    @Column(name = "target_quantity")
    private Integer targetQuantity;

    @Column(name = "ratio")
    private Integer ratio;

    @Column(name = "address")
    private String address;
    @Column(name = "policy_name")
    private String policyName;
    @Column(name = "slot")
    private String slot;
    @Column(name = "policy_id")
    private String policyId;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "ipfs")
    private String ipfs;
    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "multi_mint_yn")
    private String multiMintYn = "0";
    @Column(name = "created_at" , insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at" , insertable = true, updatable = true)
    private Date updatedAt=new Date();


}
