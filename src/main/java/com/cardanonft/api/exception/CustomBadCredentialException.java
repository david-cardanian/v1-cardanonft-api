package com.cardanonft.api.exception;


import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.interfaces.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomBadCredentialException extends RuntimeException implements CustomException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int returnCode;
	private String returnMessage;

	public CustomBadCredentialException(RETURN_CODE returnCode) {
		super(returnCode.getReturnMessage());
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnCode.getReturnMessage();
	}

	public CustomBadCredentialException(RETURN_CODE returnCode, String returnMessage) {
		super(returnCode.getReturnMessage());
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnMessage;
	}

}
