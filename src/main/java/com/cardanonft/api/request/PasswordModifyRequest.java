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
public class PasswordModifyRequest {
    @ApiModelProperty(notes = "사용자 아이디")
    private String id;
    @ApiModelProperty(notes = "변경 전 비밀번호.")
    private String oldPassword;
    @ApiModelProperty(notes = "변경 후 비밀번호")
    private String newPassword;
}
