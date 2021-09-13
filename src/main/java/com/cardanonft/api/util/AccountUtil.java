package com.cardanonft.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String encodePassword(String password){
		return bCryptPasswordEncoder.encode(password);
//		String salt = BCrypt.gensalt();
//		String hash = BCrypt.hashpw(password, salt);
//		return hash;
	}

	public boolean confirmPassword(String password, String db_password){
//		String salt = db_password.substring(0, 29);
//		String hash = BCrypt.hashpw(password, salt);
		if(password.equals(db_password)){
			return true;
		}else{
			return false;
		}
	}
	public boolean confirmPasswordEcoded(String password, String db_password){
//		String salt = db_password.substring(0, 29);
//		String hash = BCrypt.hashpw(password, salt);
//		if(hash.equals(db_password)){
		if(bCryptPasswordEncoder.matches(password, db_password)){
			return true;
		}else{
			return false;
		}
	}
	public String refinePhoneAccount(String account) {

		if (account.startsWith("82010")) {
			account = "8210" + account.substring(5);
		}

		// 직원용 가입시 JP , US , CH 미지원으로 아래와 같이 예외 처리
		// US
		if (account.startsWith("1010")) {
			account = "8210" + account.substring(4);
		}
		// CH
		if (account.startsWith("null10")) {
			account = "8210" + account.substring(6);
		}
		// JP
		if (account.startsWith("8110")) {
			account = "8210" + account.substring(4);
		}

		return account;
	}
	public static void main(String arg[]){
		System.out.println(new BCryptPasswordEncoder().encode("test12345"));
	}
}
