package com.cardanonft.api.constants;

import com.cardanonft.config.MessagesConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class C {
	/**
	 *
	 * @author Sypark
	 * 접속 클라이언트 별 API_KEY = 고정 값
	 */
	public static class API_KEY {
		public static final String V3_STAFF = "STAFF-D649A8DFC4AA7AFF3D71B37912EA8";
		public static final String V3_OWNER = "OWNER-8B1748859C1E4FC5798D1AEDF1818";
		public static final String V3_WEB = "WEB-DF9FE481874A9DBADA415A7ACFCC1";
		public static final String V3_SUPER = "SUPER-DA2C86B3F14632A145DCEAF215E9D";

		public static String getAuthorityName(String key) {
			if (V3_STAFF.equals(key)) {
				return "ROLE_USER";
			} else if (V3_OWNER.equals(key)) {
				return "ROLE_USER";
			} else if (V3_WEB.equals(key)) {
				return "ROLE_ADMIN";
			} else if (V3_SUPER.equals(key)) {
				return "ROLE_SUPER";
			}
			return null;
		}

		public static String getClientName(String key) {
			if (V3_STAFF.equals(key)) {
				return "V3_STAFF";
			} else if (V3_OWNER.equals(key)) {
				return "V3_OWNER";
			} else if (V3_WEB.equals(key)) {
				return "V3_WEB";
			} else if (V3_SUPER.equals(key)) {
				return "V3_WEB";
			}
			return null;
		}
	}

	public class TARGET_HOST {
		public static final String V2_DEFAULT_HOST = "localhost";
		public static final int V2_DEFAULT_PORT = 8080;
		public static final String V2_DEFAULT_PATH = "api/v2/auth/info";
	}

	public static final String PROXY_AUTH_KEY = "555A8B91A3A1733D9CB4C65831D13";

	public static final String ROLE_SCHEDULE_MANAGER = "ROLE_SCHEDULE_MANAGER";
	/**
	 * 아래는 V2에서 정의된 상수 값들
	 */
	public static final String IMG_SERVER_HOST = "https://img.albamapp.com";
	public static final String ALBAM_USER_ID = "-1";
	public static final boolean SET_AUTHORITY = true;

	public static class FILE_PATH {
		public static final String EXCEL_FILE = System.getProperty("user.home") + "/temp/excel/";
		public static final String PHOTO_FILE = "/home/ubuntu/temp/photo/";
		public static final String GUIDE_FILE = System.getProperty("user.home") + "/temp/guide/";
	}

	public class APP_TYPE {
		public static final int STAFF = 1;
		public static final int OWNER = 2;

		public static final int VERSION1 = 1;
		public static final int VERSION2 = 2;
	}

	public enum COUNTRY_CODE {
		SOUTH_KOREA("kr",82);

		private String domainCode;
		private int dialingCode;

		public String getDomainCode() {
			return domainCode;
		}
		public void setDomainCode(String domainCode) {
			this.domainCode = domainCode;
		}
		public int getDialingCode() {
			return dialingCode;
		}
		public void setDialingCode(int dialingCode) {
			this.dialingCode = dialingCode;
		}
		private COUNTRY_CODE(String domainCode, int dialingCode) {
			this.domainCode = domainCode;
			this.dialingCode = dialingCode;
		}
	}

	public class DEFAULT_VALUES {
		public static final int DEFAULT_ENURI_TIME = 1;
		public static final int DEFAULT_POSITION = 1;
		public static final int DEFAULT_HOUR_WAGE = 6470;
		public static final int MAX_WORK_TIME = 20;
		public static final int PASSWORD_FAIL_COUNT_INIT_HOUR = 2;
	}

	public class ERROR_CODE {
		// account
		public final static int ERROR = 0;
		public final static int SUCCESS = 1;
		public final static int NO_ID = 2;
		public final static int NO_PW = 3;
		public final static int WRONG_ID = 4;
		public final static int WRONG_PW = 5;
		public final static int ID_DUPLICATION = 6;
		public final static int WRONG_ID_TYPE =7;

		// auth
		public final static int EXPIRE_CODE = 1301;
		public final static int WRONG_CODE = 1302;
		public final static int WRONG_PHONE = 1303;
		public final static int WRONG_EMAIL = 1304;
		public final static int ALREADY_VERIFIED = 1305;
		public final static int NO_REGISTERD_PHONE = 1306;
		public final static int NO_REGISTERD_EMAIL = 1307;
	}

	public class ID_TYPE {
		public static final int PHONE = 1;
		public static final int EMAIL = 2;
		public static final int NAME = 3;
	}

	public class IMG_USE {
		public static final String PROFILE_IMG = "profile";
		public static final String REGIST_NUM = "registration_number";
		public static final String STORE_IMG = "store";
	}

	public class IS_SUPPORT_BLE {
		public static final int TRUE = 1;
		public static final int FALSE = 2;
	}

	public class IS_VERIFIED {
		public static final int TRUE = 1;
		public static final int FALSE = 0;
	}

	public class MANAGER_RIGHT {
		public static final int VIEW = 1;
		public static final int EDIT = 2;
	}

	public class MESSAGE_TYPE {
		public static final int NOTICE = 2;
		public static final int SYSTEM_NOTI = 4;
	}

	public class OS_TYPE {
		public static final int ANDROID = 1;
		public static final int IOS = 2;
		public static final int SHOPNET = 9;
	}

	public enum APP_PLATFORM {
		APNS_OWNER ("APNS"),
		APNS_STAFF("APNS"),
		GCM_OWNER("GCM"),
		GCM_STAFF("GCM");

		private String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		private APP_PLATFORM(String type) {
			this.type = type;
		}
	}

	public class POSITION {
		public static final int TYPE_PART_TIME_WORKER = 1;
		public static final int TYPE_DAILY_WORKER = 2;
		public static final int TYPE_FULL_TIME_WORKER = 3;

		public static final String PART_TIME_WORKER = "시급직";
		public static final String DAILY_WORKER = "일당직";
		public static final String FULL_TIME_WORKER = "월급직";
	}

	public class PUSH {
		public static final int ANDROID_STAFF_GCM_TYPE= 1;
		public static final int IOS_APNS_OWNER_TYPE = 2;
		public static final int IOS_APNS_STAFF_TYPE = 3;
		public static final int ANDROID_OWNER_GCM_TYPE=4;

		public static final String ANDROID_OWNER_GCM_SERVICE 		= "albam_gcm_owner";
		public static final String ANDROID_STAFF_GCM_SERVICE 		= "albam_gcm_staff";
		public static final String IOS_APNS_OWNER_SERVICE_V2 		= "albam_owner_v2_prod"; //관리자용은 v2로 추가
		public static final String IOS_APNS_OWNER_SERVICE_V2_DEBUG 	= "albam_owner_v2_dev";

		public static final String IOS_APNS_STAFF_SERVICE_V1 		= "albam_staff";  //v1 직원용 그대로 유지

		public static final String GCM = "gcm";
		public static final String APNS = "apns";
	}

	public enum PUSH_CODE {
		/*
		 *
		 * 1 근무이상자 발생 #{직원이름} 직원에 비정상 출퇴근 기록이 있습니다. staff_id, roll_id, store_id 사장 2
		 * 출근 #{직원이름} 이 출근하였습니다. staff_id, roll_id, store_id 사장 3 퇴근 #{직원이름} 이
		 * 퇴근하였습니다. staff_id, roll_id, store_id 사장 4 새로운 직원 합류 #{지원이름} 이 상점에 합류 요청을
		 * 하였습니다 staff_id, store_id 사장 5 세무사 합류 요청 #{세무사무소} 가 세무업무 대행 요청을 하였습니다
		 * accountant_id, store_id 사장 6 신규 공지사항 새로운 공지사항이 등록되었습니다 store_id,
		 * notice_id 알바생 7 합류신청 허용 어디 상점에서 합류신청을 허용했습니다. store_id 알바생 8 합류신청 거부 어디
		 * 상점에서 합류신청을 거부했습니다. store_id 알바생 9 다른 기기로 로그인 #{직원}이 기존과 다른 핸드폰(#{기기})로
		 * 로그인했습니다. staff_id, store_id 사장
		 */

		ABNORMAL(1, "매장이름 에 직원이름 직원의 비정상 출퇴근 기록이 있습니다."), // 점
		ATTENDANCE(2, "매장이름 에서 직원이름 직원이 출근했습니다."), // 점
		GOHOME(3, "매장이름 에서 직원이름 직원이 퇴근했습니다."), // 점
		REQUEST_JOIN_ALBA(4, "직원이름 님이 매장이름 매장에 합류 요청을 했습니다."), // 점
		REQUEST_JOIN_TAX_ACC(5, "직원이름 님이 매장이름 매장에 합류 요청을 했습니다."),
		NEW_NOTICE(6, "매장이름 에 새로운 공지사항이 등록되었습니다."), // 직
		ACCEPT_REQUEST(7, "매장이름 에서 합류신청을 수락했습니다."), // 직
		REJECT_REQUEST(8, "매장이름 에서 합류신청을 거절했습니다."), // 직
		LOGIN_OTHER_DEVICE(9, "login_other_device"), // 점 , %s 직원이 기존과 다른 핸드폰(%s)으로 로그인했습니다.
		SCHEDULED_CHECK_IN(10,"즐거운 출근길이 되길 응원할게요!"),
		SCHEDULED_CHECK_OUT(11,"즐거운 퇴근길! 오늘 하루도 수고 많았어요.")
		;

		private int push_code;
		private String push_message;

		private PUSH_CODE(int push_code, String push_message) {
			this.push_code = push_code;
			this.push_message = push_message;
		}

		public int getPush_code() {
			return push_code;
		}

		public void setPush_code(int push_code) {
			this.push_code = push_code;
		}

		public String getPush_message() {
			return MessagesConfig.getPropertyValue("push.code." + push_message);
		}

		public void setPush_message(String push_message) {
			this.push_message = push_message;
		}
	}



	public enum RIGHT_GROUP {
		MANAGEMENT("직원관리/직원"),
		NOTICE("공지사항/공지"),
		REPORT("월별기록/정산"),
		SCHEDULE("직원스케쥴러/내일"),
		SETTING("매장설정/설정"),
		STATUS("현재현황/오늘"),
		;

		private String desc;

		private RIGHT_GROUP(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public class RIGHT_TYPE {
		public static final int ROLLS_VIEW_WRITE = 1;
		public static final int ROLLS_WRITE = 2;
		public static final int ALL_EDIT = 3;
		public static final int ROLLS_EDIT_VIEW = 4;
		public static final int ROLLS_VIEW = 5;
	}

	public enum RIGHT {
		MANAGEMENT_JOIN_REQUEST("신규직원 합류승인/거부"),
		MANAGEMENT_STAFF_LIST("직원리스트 조회"),
		MANAGEMENT_SETTING_PROFILE("직원근무설정 - 직원프로필"),
		MANAGEMENT_SETTING_EMP_TYPE_WAGE("직원근무설정 - 고용형태 및 급여설정"),
		MANAGEMENT_STAFF_REPORT("직원근무설정 - 본인근무기록 조회 허가"),
		MANAGEMENT_STAFF_RETIRE("직원근무설정 - 퇴직처리"),
		MANAGEMENT_STAFF_INVITE("직원초대"),
		NOTICE_R("공지사항 보기"),
		NOTICE_C("공지사항 쓰기"),
		REPORT_STAFF_REPORT_R("월별 근무내역 조회"),
		REPORT_STAFF_REPORT_C("월별 근무기록 추가"),
		REPORT_SEND_REPORT("급여내역 전송하기"),
		SCHEDULE_SCHEDULE_R("직원 근무 스케쥴 보기"),
		SCHEDULE_SCHEDULE_CUD("직원 근무 스케쥴 생성/수정/삭제"),
		SCHEDULE_BREAKTIME_R("휴게시간 보기"),
		SCHEDULE_BREAKTIME_CUD("휴게시간 설정"),
		SETTING_STORE_INFO("매장정보"),
		SETTING_UPDATE_STORE_INFO("매장정보 수정"),
		SETTING_SALES("매출현황표시"),
		SETTING_CHECK_ADDRESS("직원출퇴근시 주소 확인"),
		SETTING_ENURI_TIME("시급계산단위"),
		SETTING_REG_DEVICE("알밤기기등록"),
		SETTING_STORE_PHOTO("매장사진편집"),
		SETTING_ADD_MANAGER("관리자추가"),
		SETTING_DEL_STORE("매장삭제"),
		STATUS_TIMESHEET("출퇴근 현황 조회"),
		STATUS_MISSING_DATA("출퇴근누락내역 조회"),
		STATUS_SALES("매출 조회"),
		STATUS_STAFF_REPORT_R("직원별 근무내역 조회"),
		STATUS_STAFF_REPORT_CUD("직원별 근무내역 생성/수정/삭제");

		private String desc;

		private RIGHT(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public class ROLE_TYPE {
		public static final int ADMIN = 1;
		public static final int STAFF = 2;
		public static final int OWNER = 3;
		public static final int GUEST = 4;  //보기권한
		public static final int MANAGER = 5; //전체권한
		public static final int CUSTOM_MANAGER = 6;
	}

	public class ROLLS_STATUS {
		public static final String NORMAL = "normal";
		public static final String ABNORMAL = "abnormal";
	}

	public class USER_ROLES_HISTORY {
		public final static int JOIN = 1;
		public final static int LEAVE = 2;
	}

	public class VERIFIED_TYPE {
		public final static int NO_VERIFIED = 0;
		public final static int PHONE = 1;
		public final static int EMAIL = 2;
		public final static int BOTH_VERIFIED = 3;

	}

	@Getter
	@AllArgsConstructor
	public enum CLIENT_TYPE {
		QCHAR_APP("QCHAR_APP"),
		QCHAR_ADMIN("QCHAR_ADMIN"),
		QCHAR_WEB("QCHAR_WEB"),
		QCHAR_SUPER("QCHAR_SUPER");

		private String clientType;

		public void setClientType(String clientType){
			this.clientType = clientType;
		}
	}

}
