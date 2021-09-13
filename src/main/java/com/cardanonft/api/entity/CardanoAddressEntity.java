package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cardano_address")
@Entity
public class CardanoAddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(insertable = false, name = "address_id", nullable = false)
    private String addressId;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "description")
    private String description;

    @Column(name = "nft_id")
    private Integer nftId;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at" , insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at" , insertable = true, updatable = true)
    private Date updatedAt=new Date();
}

