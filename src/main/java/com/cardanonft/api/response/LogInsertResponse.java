package com.cardanonft.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogInsertResponse {
    @ApiModelProperty(notes = "로그 지불 성공 여부 판단",required=false)
    private boolean isSuccess;

}
