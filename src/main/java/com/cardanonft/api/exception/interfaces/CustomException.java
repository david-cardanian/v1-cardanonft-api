package com.cardanonft.api.exception.interfaces;

public interface CustomException {

	public int getReturnCode();

	public void setReturnCode(int returnCode);

	public String getReturnMessage();

	public void setReturnMessage(String returnMessage);
}
