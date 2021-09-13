package com.cardanonft.api.dao;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("countPasswordEqualParams")
public class CountPasswordEqualParams {
	public CountPasswordEqualParams() {

	}

	public CountPasswordEqualParams(String _username, String _password) {
		this.username = _username;
		this.password = _password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String username;
	String password;
}
