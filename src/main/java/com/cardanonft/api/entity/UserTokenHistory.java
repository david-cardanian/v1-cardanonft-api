package com.cardanonft.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "user_token_history", schema = "cardano", catalog = "")
public class UserTokenHistory {
    @Id
    @Column(name = "history_id")
    private String historyId;

    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "epoch")
    private Integer epoch;
    @Basic
    @Column(name = "distribution_id")
    private Integer distributionId;
    @Basic
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Basic
    @Column(name = "address_id")
    private Integer addressId;
    @Basic
    @Column(name = "quantity")
    private Long quantity;
    @Basic
    @Column(name = "balance")
    private Long balance;
    @Basic
    @Column(name = "is_enabled")
    private String isEnabled="1";
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;


}
