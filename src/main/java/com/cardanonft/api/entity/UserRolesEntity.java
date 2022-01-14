package com.cardanonft.api.entity;

import com.cardanonft.api.vo.auth.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_roles", schema = "cardano", catalog = "")
public class UserRolesEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_role_id")
    private Integer userRoleId;
    @Basic
    @Column(name = "user_id")
    private String userId;

    @Basic
    @Column(name = "role_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole roleId;

    @Basic
    @Column(name = "is_enabled")
    private String isEnabled="1";
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;


}
