package com.cardanonft.api.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.TypeAlias;

@ApiModel
@TypeAlias("version")
public class Version {
	public long getVersion_id() {
		return version_id;
	}
	public void setVersion_id(long version_id) {
		this.version_id = version_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getStore_id() {
		return store_id;
	}
	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}
	public String getVersion_service() {
		return version_service;
	}
	public void setVersion_service(String version_service) {
		this.version_service = version_service;
	}
	public String getServer_version() {
		return server_version;
	}
	public void setServer_version(String server_version) {
		this.server_version = server_version;
	}
	public String getLatest_adr_version() {
		return latest_adr_version;
	}
	public void setLatest_adr_version(String latest_adr_version) {
		this.latest_adr_version = latest_adr_version;
	}
	public String getLatest_ios_version() {
		return latest_ios_version;
	}
	public void setLatest_ios_version(String latest_ios_version) {
		this.latest_ios_version = latest_ios_version;
	}
	public String getRequired_adr_version() {
		return required_adr_version;
	}
	public void setRequired_adr_version(String required_adr_version) {
		this.required_adr_version = required_adr_version;
	}
	public String getRequired_ios_version() {
		return required_ios_version;
	}
	public void setRequired_ios_version(String required_ios_version) {
		this.required_ios_version = required_ios_version;
	}
	public String getUpdate_ios_link() {
		return update_ios_link;
	}
	public void setUpdate_ios_link(String update_ios_link) {
		this.update_ios_link = update_ios_link;
	}
	public String getUpdate_adr_link() {
		return update_adr_link;
	}
	public void setUpdate_adr_link(String update_adr_link) {
		this.update_adr_link = update_adr_link;
	}
	private long version_id;
	private long user_id;
	private long store_id;

	private String version_service;
	private String server_version;

	@ApiModelProperty(value="최신 안드로이드 버전")
	private String latest_adr_version;
	@ApiModelProperty(value="최신 아이폰 버전")
	private String latest_ios_version;
	@ApiModelProperty(value="최소 안드로이드 버전")
	private String required_adr_version;
	@ApiModelProperty(value="최소 아이폰 버전")
	private String required_ios_version;
	@ApiModelProperty(value="아이폰 다운로드 링크")
	private String update_ios_link;
	@ApiModelProperty(value="안드로이드 다운로드 링크")
	private String update_adr_link;
}
