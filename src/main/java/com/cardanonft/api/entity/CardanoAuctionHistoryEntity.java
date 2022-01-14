package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cardano_auction_history")
public class CardanoAuctionHistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_history_id", nullable = false)
    private Integer auctionHistoryId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "auction_id")
    private Integer auctionId;

    @Column(name = "auction_detail_id")
    private Integer auctionDetailId;

    @Column(name = "collection_id")
    private Integer collectionId;

    @Column(name = "nft_id")
    private Integer nftId;

    /**
     * “1” outbid, “2” outbid refunded, “3” lower bid refunded,”4” winner
     */
    @Column(name = "status")
    private String status;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

}
