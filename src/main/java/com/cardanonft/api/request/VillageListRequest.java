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
public class VillageListRequest {
    @ApiModelProperty(required = true,notes = "userId")
    private String userId;
    @ApiModelProperty(required = false,notes = "continent_id")
    private String continentId;
    @ApiModelProperty(required = false,notes = "village_id")
    private String villageId;
    @ApiModelProperty(required = false,notes = "is_deployed")
    private String isDeployed;
}


