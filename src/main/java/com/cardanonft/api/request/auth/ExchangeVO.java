package com.cardanonft.api.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ExchangeVO {

	@Override
	public String toString() {
		return "ExchangeVO [access_token=" + access_token + ", platform="
				+ platform + "]";
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@ApiModelProperty(required=true)
	private String access_token;

	@ApiModelProperty(required=true, example="'iOS','ANDROID','WEB','ADMIN','SUPER'")
	private String platform;
	
}
