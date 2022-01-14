package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "cardano_transaction")
@Entity
public class CardanoTransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "transaction_id", insertable = false, nullable = false)
    private String transactionId;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "input_amount")
    private String inputAmount;

    @Column(name = "transaction_json")
    private String transactionJson;
    @Column(name = "token_json")
    private String tokenJson;
    @Column(name = "included_at")
    private String includedAt;
    @Column(name = "exec_yn")
    private String execYn = "0";
    @Column(name = "mint_yn")
    private String mintYn = "0";
    @Column(name = "refund_yn")
    private String refundYn = "0";
    @Column(name = "mint_coment")
    private String mintComent;
    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at" , insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at" , insertable = true, updatable = true)
    private Date updatedAt=new Date();
}

