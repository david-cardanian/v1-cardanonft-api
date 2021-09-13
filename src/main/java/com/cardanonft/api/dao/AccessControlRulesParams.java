package com.cardanonft.api.dao;

import org.springframework.data.annotation.TypeAlias;

@TypeAlias("accessControlRulesParams")
public class AccessControlRulesParams {
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	int store_id;
	int user_id;

	public AccessControlRulesParams() {
		this.store_id = 0;
		this.user_id = 0;
	}
}
