package com.cardanonft.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class AssetDeployRequest {
    @ApiModelProperty(required = false,notes = "mapParcelId")
    private Integer mapParcelId;
    @ApiModelProperty(required = false,notes = "userId")
    private String userId;
    @ApiModelProperty(required = false,notes = "assetId")
    private Integer assetId;
    @ApiModelProperty(required = false,notes = "villageNumber")
    private String villageNumber;
}


