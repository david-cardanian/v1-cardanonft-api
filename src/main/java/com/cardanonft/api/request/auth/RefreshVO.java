package com.cardanonft.api.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RefreshVO {
	@Override
	public String toString() {
		return "RefreshVO [refresh_token=" + refresh_token + "]";
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	@ApiModelProperty(required=true)
	private String refresh_token;
	
}
