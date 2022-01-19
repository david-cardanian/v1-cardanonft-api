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
public class UserModifyRequest {
    @ApiModelProperty(notes = "사용자 id")
    private String id;
    @ApiModelProperty(notes = "nick name")
    private String nickName;
    @ApiModelProperty(notes = "twitter")
    private String twitter;
    @ApiModelProperty(notes = "facebook")
    private String facebook;
    @ApiModelProperty(notes = "discord")
    private String discord;
}
