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
public class MapListResponse {
    @ApiModelProperty(required = false,notes = "asset_id")
    private Integer assetId;
    @ApiModelProperty(required = false,notes = "continent_id")
    private String continentId;
    @ApiModelProperty(required = false,notes = "village_id")
    private String villageId;
    @ApiModelProperty(required = false,notes = "parcelX")
    private Integer parcelX;
    @ApiModelProperty(required = false,notes = "parcelY")
    private Integer parcelY;
    @ApiModelProperty(required = false,notes = "token_name")
    private String tokenName;
    @ApiModelProperty(required = false,notes = "description")
    private String description;
    @ApiModelProperty(required = false,notes = "imgUrl")
    private String imgUrl;
    @ApiModelProperty(required = false,notes = "user_id")
    private String userId;
}


