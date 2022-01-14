package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cardano_batch_control")
public class CardanoBatchControlEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_control_id", nullable = false)
    private Integer batchControlId;

    @Column(name = "node_count")
    private Integer nodeCount;

    @Column(name = "exec_yn")
    private String execYn;

    @Column(name = "exec_pre_yn")
    private String execPreYn;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

}
