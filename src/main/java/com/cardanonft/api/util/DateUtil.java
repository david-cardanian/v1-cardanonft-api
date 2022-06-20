package com.cardanonft.api.util;


import com.mysql.cj.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/// TODO: 시간 값 처리가 필요하면 이 곳에 유틸 로직 구현
	public static String getNowDateStr(){
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(today);
	}
	public static String getNowDateStrFlat(){
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(today);
	}
	public static String getNowDateUTC(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone utc = TimeZone.getTimeZone("UTC");
		formatter.setTimeZone(utc);
		String today = formatter.format(new Date());

		return today;
	}

	public static String getDateStrFormattedYYYYMMDD(String dateStr){
		if(StringUtils.isNullOrEmpty(dateStr) || dateStr.length() < 8){
			return dateStr;
		}else{
			return dateStr.substring(0,4) + "년 "+ dateStr.substring(4,6) + "월 "+ dateStr.substring(6,8) + "일";
		}
	}
	public static String getTimeStrFormattedHHmm(String timeStr){
		if(StringUtils.isNullOrEmpty(timeStr) || timeStr.length() < 4){
			return timeStr;
		}else{
			return timeStr.substring(0,2) + "시 "+ timeStr.substring(2,4) + "분";
		}
	}
	public static String getTimeStrFormattedHHmmColon(String timeStr){
		if(StringUtils.isNullOrEmpty(timeStr) || timeStr.length() < 4){
			return timeStr;
		}else{
			return timeStr.substring(0,2) + ":"+ timeStr.substring(2,4);
		}
	}
	public static String getMMInTimeStrHHMM(String timeStr){
		if(StringUtils.isNullOrEmpty(timeStr) || timeStr.length() < 4){
			return timeStr;
		}else{
			return timeStr.substring(2,timeStr.length());
		}
	}
	public static boolean compareLunchTime(String startTime, String endTime, String nowTime){
		if(StringUtils.isNullOrEmpty(startTime) || StringUtils.isNullOrEmpty(endTime) || StringUtils.isNullOrEmpty(nowTime)){
			return false;
		}
		if(nowTime.compareTo(startTime) > 0 && nowTime.compareTo(endTime) < 0){
			return true;
		}else return false;
	}
}
