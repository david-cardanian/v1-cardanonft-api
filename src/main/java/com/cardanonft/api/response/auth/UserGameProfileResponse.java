package com.cardanonft.api.response.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
public class UserGameProfileResponse {
    @ApiModelProperty(notes = "닉네임",required=false)
    private String nickname;

    @ApiModelProperty(notes = "tokenBalance",required=false)
    private BigDecimal tokenBalance;
}
