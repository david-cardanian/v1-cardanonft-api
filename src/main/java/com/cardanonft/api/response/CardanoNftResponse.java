package com.cardanonft.api.response;


import com.cardanonft.api.constants.RETURN_CODE;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CardanoNftResponse extends DefaultResponse {
	private Object data;

	public CardanoNftResponse() {
		super();
	}

	public CardanoNftResponse(RETURN_CODE returnCode) {
		super(returnCode);
	}

	public CardanoNftResponse(RETURN_CODE returnCode, Object data) {
		super(returnCode);
		this.data = data;
	}

	public CardanoNftResponse(RETURN_CODE returnCode, String returnMessage, Object data) {
		super(returnCode, returnMessage);
		this.data = data;
	}
}
