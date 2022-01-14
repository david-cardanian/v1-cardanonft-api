package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "auth_token")
public class AuthTokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "issued_ip")
    private String issuedIp;

    @Column(name = "issued_client")
    private String issuedClient;

    @Column(name = "issued_platform")
    private String issuedPlatform;

    @Column(name = "secret")
    private String secret;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "is_enabled")
    private Boolean enabled;

    @Column(name = "is_logined")
    private Boolean logined;

    @Column(name = "logined_at")
    private Date loginedAt;

    @Column(name = "created_at")
    private Date createdAt;

}
