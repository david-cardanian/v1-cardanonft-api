package com.cardanonft.api.vo.auth;

import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("albamSignature")
@ToString
public class AlbamSignatureVO {
	String token;
	String dateStamp;
	String signature;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(String dateStamp) {
		this.dateStamp = dateStamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
