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

    @Column(name = "token_name")
    private String tokenName;

    @Column(name = "nft_name")
    private String nftName;

    @Column(name = "nft_name_kor")
    private String nftNameKor;

    @Column(name = "age")
    private String age;

    @Column(name = "description")
    private String description;

    @Column(name = "meta_str")
    private String metaStr;

    @Column(name = "ipfs")
    private String ipfs;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "gotcha_min_count")
    private Integer gotchaMinCount;

    @Column(name = "ratio")
    private Integer ratio;

    @Column(name = "target_quantity")
    private Integer targetQuantity;

    @Column(name = "mint_count")
    private Integer mintCount;

    @Column(name = "ratio_adjust_yn")
    private Integer ratioAdjustYn;

    @Column(name = "multi_mint_yn")
    private Integer multiMintYn;

    @Column(name = "param1")
    private String param1;
    @Column(name = "param2")
    private String param2;
    @Column(name = "param3")
    private String param3;
    @Column(name = "param4")
    private String param4;
    @Column(name = "param5")
    private String param5;
    @Column(name = "param6")
    private String param6;
    @Column(name = "param7")
    private String param7;
    @Column(name = "village_type_id")
    private String villageTypeId;
    @Column(name = "is_enabled")
    private String isEnabled = "1";

    private Date createdAt;
    @Column(name = "updated_at" , insertable = true, updatable = true)
    private Date updatedAt=new Date();


}
