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
public class AccountDeleteRequest {
    @ApiModelProperty(required = true,notes = "userId")
    private String userId;
    @ApiModelProperty(required = true,notes = "stakeAddress")
    private String stakeAddress;
}


