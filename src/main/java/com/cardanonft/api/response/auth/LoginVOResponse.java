package com.cardanonft.api.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.response.DefaultResponse;
import com.cardanonft.api.vo.auth.AuthToken;
import lombok.Data;

import java.util.Map;

@Data
public class LoginVOResponse extends DefaultResponse {
	public AuthToken getToken() {
		return token;
	}

	public void setToken(AuthToken token) {
		this.token = token;
	}

	AuthToken token;

	@JsonProperty("public_key")
	String publicKey;

	Map<String, Object> response;

	public LoginVOResponse(){
		
	}
	
	public LoginVOResponse(RETURN_CODE returnCode) {
		super(returnCode);
	}
	
	public LoginVOResponse(RETURN_CODE returnCode, AuthToken token) {
		super(returnCode);
		this.token = token;
	}

	public LoginVOResponse(RETURN_CODE returnCode, Map<String, Object> response) {
		super(returnCode);
		this.response = response;
	}

	public LoginVOResponse(RETURN_CODE returnCode, AuthToken token, String publicKey) {
		super(returnCode);
		this.token = token;
		this.publicKey = publicKey;
	}
}
