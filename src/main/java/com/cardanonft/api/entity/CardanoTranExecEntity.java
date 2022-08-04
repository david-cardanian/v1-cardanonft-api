package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cardano_tran_exec")
public class CardanoTranExecEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tran_exec_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tranExecId;

    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * “1” 송금, “2” 민트, “3” NFT발송, “4” burn
     */
    @Column(name = "exec_type")
    private String execType;

    @Column(name = "exec_comment")
    private String execComment;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "to_stake_address")
    private String toStakeAddress;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "minting_ada")
    private Long mintingAda;

    @Column(name = "policy_name")
    private String policyName;

    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "nft_id")
    private String nftId;

    @Column(name = "collection_id")
    private Integer collectionId;

    @Column(name = "slot")
    private String slot;

    @Column(name = "minting_count")
    private Integer mintingCount;

    @Column(name = "minted_count")
    private Integer mintedCount;

    @Column(name = "ifps")
    private String ifps;

    @Column(name = "node_number")
    private String nodeNumber;

    @Column(name = "send_all_yn")
    private String sendAllYn;

    @Column(name = "except_fee_yn")
    private String exceptFeeYn;

    @Column(name = "refund_yn")
    private String refundYn;

    @Column(name = "exec_yn")
    private String execYn;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

    @Column(name = "asset_str")
    private String assetStr;

    @Column(name = "meta_str")
    private String metaStr;

    @Column(name = "token_str")
    private String tokenStr;

    @Column(name = "return_msg")
    private String returnMsg;

}
