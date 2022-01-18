package com.cardanonft.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "user", schema = "cardano", catalog = "")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Basic
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "nick_name")
    private String nickName;
    @Basic
    @Column(name = "twitter")
    private String twitter;
    @Basic
    @Column(name = "discord")
    private String discord;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "address_road")
    private String addressRoad;
    @Basic
    @Column(name = "address_detail")
    private String addressDetail;
    @Basic
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "terms_user")
    private String termsUser="1";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "terms_privacy")
    private String termsPrivacy="1";
    @Basic
    @Column(name = "unique_number")
    private Integer uniqueNumber;
    @Basic
    @Column(name = "is_enabled")
    private String isEnabled="1";
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
