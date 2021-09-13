package com.cardanonft.api.response;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.interfaces.CustomException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DefaultResponse {

	@SerializedName("returnCode")  // 결과 코드
	private int returnCode;
	@SerializedName("returnMessage")  // 결과 코드
	private String returnMessage;

	public DefaultResponse() {
	}

	public DefaultResponse(HttpRequestMethodNotSupportedException e) {
		returnCode = HttpStatus.METHOD_NOT_ALLOWED.value();
		returnMessage = e.getMessage();
	}

	public DefaultResponse(MissingServletRequestParameterException e) {
		returnCode = HttpStatus.BAD_REQUEST.value();
		returnMessage = e.getMessage();
	}

	public DefaultResponse(CustomException e) {
		returnCode = e.getReturnCode();
		returnMessage = e.getReturnMessage();
	}

	public DefaultResponse(RETURN_CODE returnCode) {
		this.returnCode = returnCode.getReturnCode();
		returnMessage = returnCode.getReturnMessage();
	}

	public DefaultResponse(RETURN_CODE returnCode, Exception e) {
		this.returnCode = returnCode.getReturnCode();
		returnMessage = e.getMessage();
	}

	public DefaultResponse(RETURN_CODE returnCode, String msg) {
		this.returnCode = returnCode.getReturnCode();
		returnMessage = msg;
	}
	public DefaultResponse(int returnCode, String msg) {
		this.returnCode = returnCode;
		returnMessage = msg;
	}
}
