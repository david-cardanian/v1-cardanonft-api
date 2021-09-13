package com.cardanonft.api.response;


import com.cardanonft.api.constants.RETURN_CODE;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CardanoNftDefaultResponse<T> {
	private int returnCode;
	private String returnMessage;
	private T response;

	public CardanoNftDefaultResponse(RETURN_CODE returnCode) {
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnCode.getReturnMessage();
	}

	public CardanoNftDefaultResponse(RETURN_CODE returnCode, T response) {
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnCode.getReturnMessage();
		this.response = response;
	}

	public CardanoNftDefaultResponse(int returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
}
