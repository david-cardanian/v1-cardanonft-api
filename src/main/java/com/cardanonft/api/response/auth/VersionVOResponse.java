package com.cardanonft.api.response.auth;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.response.DefaultResponse;
import com.cardanonft.api.vo.system.SystemNotices;
import com.cardanonft.api.vo.system.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class VersionVOResponse extends DefaultResponse {
	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<SystemNotices> getNotices() {
		return notices;
	}

	public void setNotices(List<SystemNotices> notices) {
		this.notices = notices;
	}

	public VersionVOResponse(){
		
	}
	
	public VersionVOResponse(RETURN_CODE returnCode) {
		super(returnCode);
	}
	
	public VersionVOResponse(RETURN_CODE returnCode, Version _version, List<SystemNotices> _notices) {
		super(returnCode);

		this.version = _version;
		this.notices = _notices;
	}
	
	@ApiModelProperty(value="버전 정보")
	private Version version;
	@ApiModelProperty(value="시스템 공지")
	private List<SystemNotices> notices;

}
