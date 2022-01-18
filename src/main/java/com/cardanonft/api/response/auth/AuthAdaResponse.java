package com.cardanonft.api.response.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@ApiModel
@Getter
@Setter
public class AuthAdaResponse {
    @ApiModelProperty(required = true,notes = "address")
    private String address;
    @ApiModelProperty(required = true,notes = "authAda")
    private String authAda;
}


