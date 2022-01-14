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
public class LoginVO extends IssueVO {
    @ApiModelProperty(notes = "사용자 id")
    private String id;
    @ApiModelProperty(notes = "로그인 비밀번호 평문")
    private String password;
    @ApiModelProperty(notes = "로그인 비밀번호 암호문 sha256")
    private String password_enc;
    @ApiModelProperty(notes = "로그인 비밀번호 암호문 bcrypt")
    private String password_enc2;
    @ApiModelProperty(required = false, example = "'iOS','ANDROID','WEB','WINDOWS','MAC'")
    private String os_type;

    @ApiModelProperty(required = false)
    private String os_version;

    @ApiModelProperty(required = false)
    private String device_name;

    @ApiModelProperty(required = false)
    private String uuid;

    @ApiModelProperty(required = false)
    private String browser_info;

    @ApiModelProperty(required = false)
    private String app_version;

    @Deprecated
    private String platform;
}
