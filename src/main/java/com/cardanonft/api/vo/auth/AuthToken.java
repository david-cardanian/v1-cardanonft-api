package com.cardanonft.api.vo.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

@TypeAlias("authToken")
@ToString
@Getter
@Setter
public class AuthToken {

	public
	String token;
	String secret;
	String user_id;
	Date created_at;
	Date expired_at;
	String refresh_token;
	String issued_ip;
	String issued_client;
	String issued_platform;

	int is_logined;
	Date logined_at;
	String uuid;
	String referer;
}
