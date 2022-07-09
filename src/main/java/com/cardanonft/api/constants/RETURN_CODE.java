package com.cardanonft.api.constants;

import com.cardanonft.config.MessagesConfig;

public enum RETURN_CODE {

	SUCCESS(200, "success"),
	BAD_REQUEST(400,"bad_request"),     //   필수 요청 파라미터가 없습니다.
	BAD_REQUEST_PARAM_LENGTH(400,"bad_request_param_length"),     //   필수 요청 파라미터 갯수가 일치하지 않습니다.
	BAD_REQUEST_NOT_LENGTH(400,"bad_request_not_length"),     //   파라미터 길이를 확인하여 주십시오.
	BAD_REQUEST_PARAM_TYPE(400,"bad_request_param_type"),     //   알수 없는 타입 입니다.
	INCORRECT_ATTEMPT(400, "incorrect_attempt"),              //    잘못된 시도입니다.
	EXPIRED_TOKEN(403, "expired_token"),     //   토큰이 만료되었습니다.
	UNKNOWN_API_KEY(404, "unknown_api_key"),     //   요청 KEY를 찾을 수 없습니다.
	ERROR(500, "error"),     //   Error
	DB_ERROR(503, "db_error")     //   DATABASE ERROR
	/// Credential 코드, 인증, 권한 1000~
	,BAD_CREDENTIAL_NO_KEY(1000, "bad_credential_no_key") // 유효하지 않은 접근입니다.
	,BAD_CREDENTIAL_INVALID(1001, "bad_credential_invalid") // 토큰이 유효하지 않습니다.
	,BAD_CREDENTIAL_EXPIRED(1002, "bad_credential_expired") // 토큰이 만료되었습니다.
	,BAD_CREDENTIAL_KEY(1003, "권한이 없습니다.")
	,WRONG_ACCESS_TOKEN(1004, "세션이 만료되었습니다.")
	,NEED_AUTHORITY(1005, "need_authority") // 인증정보가 유효하지 않습니다.
	,INVALID_AUTHORITY(1006, "invalid_authority") // 인증정보가 유효하지 않습니다.
	,NEED_LOGIN(1007, "need_login") // 로그인이 필요한 기능입니다.
	// 회원가입 & 본인인증 1100~
	,NO_ID(1101, "가입하실 아이디를 입력해주세요.")
	,NO_PASSWORD(1102, "비밀번호를 입력해주세요.")
	,WRONG_ID(1103, "등록할 수 없는 아이디입니다.")
	,WRONG_PASSWORD(1104, "영문, 숫자를 포함한 7~30자 입력")
	,ID_DUPLICATION(1105, "already_id")
	,HOSPITAL_DUPLICATION(1135, "already_hospital")
	,SIGN_IN_FAIL(1106, "아이디 혹은 패스워드가 일치하지 않습니다.")
	,EXPIRE_CODE(1107, "expire_code")//인증코드가 만료되었습니다
	,WRONG_CODE(1108, "wrong_code") //인증코드를 확인 후 다시 입력해주세요.
	,WRONG_PHONE(1109, "회원가입시 휴대폰 번호와 일치하지 않습니다.")
	,WRONG_EMAIL(1110, "회원가입시 이메일과 일치하지 않습니다.")
	,ALREADY_VERIFIED(1111, "이미 인증된 계정입니다.")
	,WRONG_ACCOUNT(1112, "wrong_account") // 아이디 혹은 비밀번호를 잘못 입력하셨습니다.
	,NO_REGISTERD_PHONE(1113, "등록된 휴대폰이 없습니다.")
	,NO_REGISTERD_EMAIL(1114, "등록된 이메일이 없습니다.")
	,NEED_VERIFICATION(1115, "계정에 대한 인증이 필요합니다.")
	,WRONG_ID_TYPE(1116, "계정 type 이 올바르지 않습니다.")
	,WRONG_OLD_PASSWORD(1117, "wrong_old_password")//현재 비밀번호가 일치하지 않습니다.")
	,WRONG_NEW_PASSWORD(1118, "비밀번호는 숫자+영문 혼합 7자리 이상입니다.")
	,SAME_CURRENT_PASSWORD(1119, "현재 비밀번호와 다르게 설정해주세요.")
	,NO_ACCOUNT(1120, "no_account") // 존재하지 않는 계정입니다.
	,TOO_MANY_ACCOUNT(1121, "too_many_account")// 이미 존재하는 계정 입니다.
	,WRONG_RIGHTS(1122, "wrong_rights")//"해당 기능에 대한 접근 권한이 없습니다.")
	,EXCEED_AUTH_CODE_FAIL_COUNT(1123, "exceed_auth_code_fail_count")//"인증코드 입력오류 회수가 3회를 초과했습니다. 인증코드를 다시 발송하시기 바랍니다..")
	,NEED_TERMS_THIRD(1124, "need_terms_third")//"개인정보 제3자 제공 동의를 하셔야 사용가능합니다.")
	,NEED_SSNO(1125, "need_ssno")//"주민번호를 등록하셔야 사용가능합니다.")
	,NO_FILE(1310, "파일이 없습니다")
	,ALREADY_REGISTER_STORE(1602, "이미 등록한 병원입니다.")
	,ALREADY_REQUEST_STORE(1603, "이미 등록신청한 병원입니다.")
	,NOT_REGISTER_STORE(1604, "등록하지 않은 병원입니다.")
	,ALREADY_EXIST_STORE(1605, "올바른 store id 를 적어주세요.")
	,ALREADY_GET_STORE(1606, "이미 해당 병원에 등록되어 있는 환자입니다.")
	,NO_SERVICE(1607, "현재 지원하지 않는 기능입니다. 고객센터에 문의해주세요.")
	,BLOCK_STORE(1608, "서비스 유료 결제가 필요합니다. 고객센터에 문의해주세요.")
	,DATA_TOO_LONG(1804, "메시지가 너무 깁니다.")
	,DATA_TYPE_WRONG(1805, "데이터 타입이 올바르지 않습니다.")
	,ALREADY_EXIST_MANAGER(1806, "이미 해당 병원에 관리자로 등록되어 있는 환자입니다.")
	,NO_DATA(1807, "검색 결과가 없습니다.")
	,NO_AVAILABLE_BEACON(1808, "등록번호가 일치하지 않습니다.")
	,TIME_INCORRECT(1809, "시스템 시간을 현재시간으로 설정해주세요.")
	,ACCOUNT_IS_LOCKED(1318, "auth_is_locked")
	,NOT_USERNM_EMOJI(1813, "not_usernm_emoji")     //   이름에 특수문자를 쓰실 수 없습니다.
	,NOT_MACADDR(9001,"not_macaddr")                              //  MAC 주소를 확인하여주십시오
    ,INSUFFICIENT_LOG_TOKEN(1814, "insufficient_log_token")
	//스케쥴러 추가
	,CAN_NOT_CHANGE_DAY(1900, "can_not_change_day") //이미 예약이 존재하는 스케줄은 일자,시간을 수정할 수 없습니다. 예약취소 후 수정해주세요.
	,CAN_NOT_DELETE_MEDICAL_SUBJECT(1901, "can_not_delete_medical_subject") //이미 진료일정이 존재하는 진료과목은 수정할 수 없습니다.
	,CAN_NOT_DELETE_TREATMENT_TYPE(1902, "can_not_delete_treatment_type") //이미 진료일정이 존재하는 진료종류는 수정할 수 없습니다.
	,CAN_NOT_DELETE_OFFICE(1903, "can_not_delete_office") //이미 진료일정이 존재하는 진료실은 수정할 수 없습니다.

	//예약
	,ALREADY_FULL_RESERVATION(400, "already_full_reservation")


	// 게임 데이터
	,GAME_HASH_MATCH_ERROR(2000, "game_hash_match_error")
	;

	private int returnCode;
	private String returnMessage;
	private String locale;

	RETURN_CODE(int returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return MessagesConfig.getPropertyValue("return.code." + returnMessage);
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}
