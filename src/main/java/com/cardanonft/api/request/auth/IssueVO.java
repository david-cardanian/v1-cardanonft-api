package com.cardanonft.api.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class IssueVO {
	@ApiModelProperty(required=true)
	public String account;

	@ApiModelProperty(required=true, example="'iOS','ANDROID','WEB','ADMIN','SUPER'")
	public String platform;

    @ApiModelProperty(value = "구글 idToken")
	public String google_id_token;

	@Override
	public String toString() {
		return "IssueVO [account=" + account + ", platform=" + platform + "]";
	}
}
