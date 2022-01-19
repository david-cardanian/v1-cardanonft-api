package com.cardanonft.api.response.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
@Builder
public class UserProfileVOResponse {
    @ApiModelProperty(notes = "닉네임",required=false)
    private String nickname;
    @ApiModelProperty(notes = "전화번호",required=false)
    private String twitter;
    @ApiModelProperty(notes = "id",required=false)
    private String facebook;
    @ApiModelProperty(notes = "인증코드",required=false)
    private String discord;
}
