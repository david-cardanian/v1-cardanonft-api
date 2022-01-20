package com.cardanonft.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class MapParcelSearchResponse {
    @ApiModelProperty(required = false,notes = "mapParcelId")
    private Integer mapParcelId;
    @ApiModelProperty(required = false,notes = "continent_id")
    private String continentId;
    @ApiModelProperty(required = false,notes = "village_id")
    private String villageId;
    @ApiModelProperty(required = false,notes = "user_id")
    private String userId;
    @ApiModelProperty(required = false,notes = "villageType")
    private String villageType;
    @ApiModelProperty(required = false,notes = "villageTypeUrl")
    private String villageTypeUrl;
    @ApiModelProperty(required = false,notes = "nftImgUrl")
    private String nftImgUrl;
    @ApiModelProperty(required = false,notes = "description")
    private String description;
    @ApiModelProperty(required = false,notes = "userImgUrl")
    private String userImgUrl;
    @ApiModelProperty(required = false,notes = "parcelX")
    private Integer parcelX;
    @ApiModelProperty(required = false,notes = "parcelY")
    private Integer parcelY;
    @ApiModelProperty(required = false,notes = "landType 1 : land 2 : water 3 : unavailable land ")
    private Integer landType;
    @ApiModelProperty(required = false,notes = "popupImgUrl")
    private String popupImgUrl;
}


