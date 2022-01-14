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
public class AccountListRequest {
    @ApiModelProperty(required = true,notes = "userId")
    private String userId;
}


