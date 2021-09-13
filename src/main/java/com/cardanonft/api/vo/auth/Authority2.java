package com.cardanonft.api.vo.auth;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("authority2")
public class Authority2 {

	private String username;
	private String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [username=" + username + ", authority=" + authority + "]";
	}

}
