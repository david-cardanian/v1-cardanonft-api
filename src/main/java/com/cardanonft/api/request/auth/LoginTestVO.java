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
public class LoginTestVO extends IssueVO {
    @ApiModelProperty(notes = "사용자 id")
    private String id;
    @ApiModelProperty(notes = "로그인 비밀번호 평문")
    private String password;

}
