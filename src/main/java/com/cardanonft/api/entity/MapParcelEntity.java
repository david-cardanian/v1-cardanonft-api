package com.cardanonft.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "map_parcel")
@Entity
public class MapParcelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(insertable = false, name = "map_parcel_id", nullable = false)
    private Integer mapParcelId;

    @Column(name = "continent_id")
    private String continentId;

    @Column(name = "village_id")
    private String villageId;

    @Column(name = "user_id")
    private String userId;
    /*
     * 1 : land 2 : water 3 : unavailable land
     * */
    @Column(name = "land_type")
    private Integer landType;

    @Column(name = "village_nft_id")
    private Integer villageNftId;
    @Column(name = "user_img_url")
    private String userImgUrl;
    @Column(name = "village_direction")
    private String villageDirection;
    @Column(name = "parcel_x_2d")
    private Integer parcelX2d;

    @Column(name = "parcel_y_2d")
    private Integer parcelY2d;

    @Column(name = "parcel_x_3d")
    private Integer parcelX3d;

    @Column(name = "parcel_y_3d")
    private Integer parcelY3d;

    @Column(name = "pixcel_x_2d")
    private Integer pixcelX2d;

    @Column(name = "pixcel_y_2d")
    private Integer pixcelY2d;

    @Column(name = "pixcel_x_3d")
    private Integer pixcelX3d;

    @Column(name = "pixcel_y_3d")
    private Integer pixcelY3d;

    @Column(name = "moon_onoff")
    private String moonOnoff = "1";
    @Column(name = "is_enabled")
    private String isEnabled = "1";

    @Column(name = "mint_yn")
    private String mintYn = "0";

    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = true, updatable = true)
    private Date updatedAt;
}

