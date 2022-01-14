package com.cardanonft.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "password_auth_code", schema = "cardano", catalog = "")
public class PasswordAuthCodeEntity {
    @Id
    @GeneratedValue
    @Column(name = "auth_code_id")
    private String authCodeId;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "auth_code")
    private String authCode;
    @Basic
    @Column(name = "fail_count")
    private int failCount;
    @Basic
    @Column(name = "is_enabled")
    private String isEnabled="1";
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;
}
