package com.cardanonft.api.exception;


import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.interfaces.CustomException;

public class CustomBatchException extends RuntimeException implements CustomException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int returnCode;
	private String returnMessage;

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public CustomBatchException(RETURN_CODE returnCode) {
		super(returnCode.getReturnMessage());
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnCode.getReturnMessage();
	}

	public CustomBatchException(RETURN_CODE returnCode, String returnMessage) {
		super(returnCode.getReturnMessage());
		this.returnCode = returnCode.getReturnCode();
		this.returnMessage = returnMessage;
	}

}
