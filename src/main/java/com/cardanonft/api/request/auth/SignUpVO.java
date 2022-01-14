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
public class SignUpVO {
    @ApiModelProperty(notes = "사용자 id")
    private String id;
    @ApiModelProperty(notes = "user family id")
    private int userFamilyId;
    @ApiModelProperty(notes = "로그인 비밀번호 평문")
    private String password;
    @ApiModelProperty(notes = "신규 로그인 비밀번호 평문")
    private String passwordNew;
    @ApiModelProperty(notes = "로그인 비밀번호 암호문 sha256")
    private String password_enc;
    @ApiModelProperty(notes = "로그인 비밀번호 암호문 bcrypt")
    private String password_enc2;
    @ApiModelProperty(notes = "이름")
    private String name;
    @ApiModelProperty(notes = "우편번호")
    private String postalCode;
    @ApiModelProperty(notes = "주소")
    private String addressRoad;
    @ApiModelProperty(notes = "주소 상세")
    private String addressDetail;
    @ApiModelProperty(notes = "휴대폰 번호")
    private String phoneNumber;
    @ApiModelProperty(notes = "주민번호 앞자리")
    private String ssno;
    @ApiModelProperty(notes = "주민번호 뒷자리")
    private String ssno2;
    @ApiModelProperty(notes = "알림톡수신거부")
    private String alimtalkReceiveDeny;
    @ApiModelProperty(notes = "서비스이용약관동의여부")
    private String termsUser;
    @ApiModelProperty(notes = "개인정보처리방침동의여부")
    private String termsPrivacy;
    @ApiModelProperty(notes = "개인정보제3자제공동의여부")
    private String termsThird;
    @ApiModelProperty(notes = "위치정보이용동의여부")
    private String termsLocation;
    @ApiModelProperty(notes = "마케팅정보수신동의여부")
    private String termsMarketing;
}
