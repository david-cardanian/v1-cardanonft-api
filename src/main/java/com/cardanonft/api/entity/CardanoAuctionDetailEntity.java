package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cardano_auction_detail")
@Data
public class CardanoAuctionDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "auction_detail_id")
    private Integer auctionDetailId;

    @Column(name = "lowest_bid_price")
    private Integer lowestBidPrice;

    @Column(name = "highst_bid_price")
    private Integer highstBidPrice;

    @Column(name = "add_bid_price")
    private Integer addBidPrice;

    @Column(name = "nft_address")
    private String nftAddress;

    @Column(name = "is_enabled")
    private String isEnabled = "1";

}
