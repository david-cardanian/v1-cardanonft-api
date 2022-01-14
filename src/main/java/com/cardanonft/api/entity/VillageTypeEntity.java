package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "village_type")
public class VillageTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_type_id", nullable = false)
    private Integer villageTypeId;

    @Column(name = "continent_id")
    private String continentId;

    @Column(name = "village_id")
    private String villageId;

    @Column(name = "village_type")
    private Integer villageType;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "note")
    private String note;

    @Column(name = "is_enabled")
    private String isEnabled = "1";
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;

}
