package com.cardanonft.view.util;

import com.amazonaws.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class CardanoNftUtils {
    public static boolean isLogined(HttpSession session) {
        if (session.getAttribute("auth_token") == null) {
            return false;
        }
        String token = session.getAttribute("auth_token") + "";
        if (!StringUtils.isNullOrEmpty(token)) {
            return true;
        }
        return false;
    }
    public static String getSessionUserId(HttpSession session) {
        if (session.getAttribute("user_id") == null) {
            return null;
        }
        String userId = session.getAttribute("user_id") + "";
        if (!StringUtils.isNullOrEmpty(userId)) {
            return userId;
        }
        return null;
    }
    public static void setProfile(ModelAndView modelAndView) {
        String profile = System.getProperty("spring.profiles.active");
        modelAndView.addObject("profile",profile);
    }
    public static String getProfile() {
        String profile = System.getProperty("spring.profiles.active");
        return profile;
    }
    public static boolean loginCheck(ModelAndView modelAndView, HttpSession session) {
        if(!CardanoNftUtils.isLogined(session)){
            modelAndView.setViewName( "auth/login");
            return false;
        }else{
           return true;
        }
    }
    public static String getAuthToken(HttpSession session) {
        if (session.getAttribute("auth_token") == null) {
            return "";
        }
        String token = session.getAttribute("auth_token") + "";
        if (!StringUtils.isNullOrEmpty(token)) {
            return token;
        }
        return "";
    }
    public static boolean isAlimtalkMultiUse(HttpSession session){
        if (session.getAttribute("alimtalk_multi_use") == null) {
            return false;
        }
        String alimtalkMultiUse = session.getAttribute("alimtalk_multi_use") + "";
        if (!StringUtils.isNullOrEmpty(alimtalkMultiUse) && "1".equals(alimtalkMultiUse)) {
            return true;
        }else return false;
    }
    public static boolean isReservationTableUse(HttpSession session){
        if (session.getAttribute("reservation_table_use") == null) {
            return false;
        }
        String reservationTableUse = session.getAttribute("reservation_table_use") + "";
        if (!StringUtils.isNullOrEmpty(reservationTableUse) && "1".equals(reservationTableUse)) {
            return true;
        }else return false;
    }
    public static  int stringToInt(String numberStr){
        int number = 0;
        try {
            number = Integer.parseInt(numberStr);
        }catch (Exception e){
        }
        return number;
    }
    public static  long stringToLong(String numberStr){
        long number = 0;
        try {
            number = Long.parseLong(numberStr);
        }catch (Exception e){
        }
        return number;
    }
}
