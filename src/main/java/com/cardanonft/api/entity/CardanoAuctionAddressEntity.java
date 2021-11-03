package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cardano_address")
@Data
public class CardanoAuctionAddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "nft_collection_id")
    private Integer nftCollectionId;

    @Column(name = "is_enabled")
    private String isEnabled = "1";

}
