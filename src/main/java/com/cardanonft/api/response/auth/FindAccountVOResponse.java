package com.cardanonft.api.response.auth;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.request.auth.FindAccountVO;
import com.cardanonft.api.response.DefaultResponse;
import lombok.Data;

import java.util.Map;

@Data
public class FindAccountVOResponse extends DefaultResponse {
	public FindAccountVO getFindIdVO() {
		return account;
	}

	public void setFindIdVO(FindAccountVO findAccountVO) {
		this.account = findAccountVO;
	}

	FindAccountVO account;

	Map<String, Object> response;

	public FindAccountVOResponse(){

	}

	public FindAccountVOResponse(RETURN_CODE returnCode) {
		super(returnCode);
	}

	public FindAccountVOResponse(RETURN_CODE returnCode, FindAccountVO account) {
		super(returnCode);
		this.account = account;
	}

	public FindAccountVOResponse(RETURN_CODE returnCode, Map<String, Object> response) {
		super(returnCode);
		this.response = response;
	}
}
