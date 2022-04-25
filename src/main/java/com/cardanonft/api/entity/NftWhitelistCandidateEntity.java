package com.cardanonft.api.entity;

import com.cardanonft.api.entity.enums.WhitelistType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "nft_whitelist_candidate", schema = "cardano", catalog = "")
public class NftWhitelistCandidateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(insertable = false, name = "id", nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private WhitelistType type;
    @Basic
    @Column(name = "stake_address")
    private String stakeAddress;
    @Basic
    @Column(name = "deleted")
    private int deleted;
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;
}
