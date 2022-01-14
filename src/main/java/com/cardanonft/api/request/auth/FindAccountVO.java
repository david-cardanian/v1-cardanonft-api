package com.cardanonft.api.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class FindAccountVO extends IssueVO {
    @ApiModelProperty(notes = "이름",required=false)
    private String name;
    @ApiModelProperty(notes = "전화번호",required=false)
    private String phoneNumber;
    @ApiModelProperty(notes = "id",required=false)
    private String id;
    @ApiModelProperty(notes = "인증코드",required=false)
    private String authCode;
}
