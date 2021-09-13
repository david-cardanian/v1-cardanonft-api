package com.cardanonft.api.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

@ApiModel
@TypeAlias("systemNotices")
public class SystemNotices {
	public long getSystem_notice_id() {
		return system_notice_id;
	}
	public void setSystem_notice_id(long system_notice_id) {
		this.system_notice_id = system_notice_id;
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
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_contents() {
		return notice_contents;
	}
	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}
	public boolean isIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	public boolean isIs_blocked() {
		return is_blocked;
	}
	public void setIs_blocked(boolean is_blocked) {
		this.is_blocked = is_blocked;
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
	private long system_notice_id;
	private long user_id;
	private long store_id;
	@ApiModelProperty(value="공지사항 타이틀")
	private String notice_title;
	@ApiModelProperty(value="공지사항 내용")
	private String notice_contents;
	private boolean is_enabled;
	@ApiModelProperty(value="시스템 접근 제한, True이면 서비스 접속 불가")
	private boolean is_blocked;
	private Date created_at;
	private Date updated_at;

}
