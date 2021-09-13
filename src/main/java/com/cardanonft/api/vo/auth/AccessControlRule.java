package com.cardanonft.api.vo.auth;

import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

@TypeAlias("accessControlRule")
public class AccessControlRule {
	public int getAccess_control_rule_id() {
		return access_control_rule_id;
	}
	public void setAccess_control_rule_id(int access_control_rule_id) {
		this.access_control_rule_id = access_control_rule_id;
	}
	public String getRule_type() {
		return rule_type;
	}
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	public String getRule_pattern() {
		return rule_pattern;
	}

	public int getRule_server() {
		return rule_server;
	}

	public void setRule_server(int rule_server) {
		this.rule_server = rule_server;
	}

	public void setRule_pattern(String rule_pattern) {
		this.rule_pattern = rule_pattern;
	}
	public String getRedirect_path() {
		return redirect_path;
	}
	public void setRedirect_path(String redirect_path) {
		this.redirect_path = redirect_path;
	}
	public boolean isIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	int access_control_rule_id;
	String rule_type;
	String rule_pattern;
	int rule_server;
	String redirect_path;
	boolean is_enabled;
	int weight;
	int user_id;
	int store_id;
	Date created_at;
	Date updated_at;
}
