package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "account_auth_ada")
public class AccountAuthAdaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "auth_ada_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authAdaId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "auth_ada")
    private String authAda;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

}
