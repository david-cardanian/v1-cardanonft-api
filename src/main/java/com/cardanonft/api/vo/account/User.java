package com.cardanonft.api.vo.account;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.util.StringUtils;

@TypeAlias("user")
public class User {

	private String user_id;
	private int is_verified;
	private String email;
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phone4;
	private String first_name;
	private String last_name;
	private String username;
	private String birthday;
	private String password;
	private int route;
	private int user_type;
	private int is_enabled;
	private int os_type;
	private int app_type;
	private String device_name;
	private String device_uuid;
	private String device_uuid_owner;
	private String device_uuid_staff;
	private int is_auto_bluetooth;
	private int is_on_alarm;
	private int is_support_ble;
	private String img_server_host;
	private String profile_img;
	private String push_token;
	private String push_token_owner;
	private String push_token_staff;
	private String user_id_orig;
	private int is_original_user;
	private int version;
	private int is_first;
	private String login_staff;

	public String getLogin_staff() {
		return login_staff;
	}

	public void setLogin_staff(String login_staff) {
		this.login_staff = login_staff;
	}

	public String getPush_token_owner() {
		return push_token_owner;
	}

	public void setPush_token_owner(String push_token_owner) {
		this.push_token_owner = push_token_owner;
	}

	public String getPush_token_staff() {
		return push_token_staff;
	}

	public void setPush_token_staff(String push_token_staff) {
		this.push_token_staff = push_token_staff;
	}

	public int getIs_first() {
		return is_first;
	}

	public void setIs_first(int is_first) {
		this.is_first = is_first;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getImg_server_host() {
		return img_server_host;
	}

	public void setImg_server_host(String img_server_host) {
		this.img_server_host = img_server_host;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getPhone4() {
		return phone4;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	public String getPhone() {
		if (StringUtils.isEmpty(phone)) {
			if (StringUtils.isEmpty(phone1) || StringUtils.isEmpty(phone2) || StringUtils.isEmpty(phone3) || StringUtils.isEmpty(phone4)) {
				return null;
			}
			return phone1 + phone2.replaceFirst("0", "") + phone3 + phone4;
		} else {
			return phone;
		}
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPhone(String phone1, String phone2, String phone3, String phone4) {
		this.phone = phone1 + phone2.replaceFirst("0", "") + phone3 + phone4;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getRoute() {
		return route;
	}

	public void setRoute(int route) {
		this.route = route;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_uuid() {
		return device_uuid;
	}

	public void setDevice_uuid(String device_uuid) {
		this.device_uuid = device_uuid;
	}

	public String getDevice_uuid_owner() {
		return device_uuid_owner;
	}

	public void setDevice_uuid_owner(String device_uuid_owner) {
		this.device_uuid_owner = device_uuid_owner;
	}

	public String getDevice_uuid_staff() {
		return device_uuid_staff;
	}

	public void setDevice_uuid_staff(String device_uuid_staff) {
		this.device_uuid_staff = device_uuid_staff;
	}

	public String getPush_token() {
		return push_token;
	}

	public void setPush_token(String push_token) {
		this.push_token = push_token;
	}

	public int getIs_verified() {
		return is_verified;
	}

	public void setIs_verified(int is_verified) {
		this.is_verified = is_verified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(int is_enabled) {
		this.is_enabled = is_enabled;
	}

	public int getApp_type() {
		return app_type;
	}

	public void setApp_type(int app_type) {
		this.app_type = app_type;
	}

	public int getOs_type() {
		return os_type;
	}

	public void setOs_type(int os_type) {
		this.os_type = os_type;
	}

	public int getIs_support_ble() {
		return is_support_ble;
	}

	public void setIs_support_ble(int is_support_ble) {
		this.is_support_ble = is_support_ble;
	}

	public int getIs_auto_bluetooth() {
		return is_auto_bluetooth;
	}

	public void setIs_auto_bluetooth(int is_auto_bluetooth) {
		this.is_auto_bluetooth = is_auto_bluetooth;
	}

	public int getIs_on_alarm() {
		return is_on_alarm;
	}

	public void setIs_on_alarm(int is_on_alarm) {
		this.is_on_alarm = is_on_alarm;
	}

	public String getUser_id_orig() {
		return user_id_orig;
	}

	public void setUser_id_orig(String user_id_orig) {
		this.user_id_orig = user_id_orig;
	}

	public int getIs_original_user() {
		return is_original_user;
	}

	public void setIs_original_user(int is_original_user) {
		this.is_original_user = is_original_user;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", is_verified=" + is_verified + ", email=" + email + ", phone=" + phone + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", phone3=" + phone3 + ", phone4=" + phone4 + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", username=" + username + ", birthday=" + birthday + ", password=" + password + ", route=" + route + ", user_type=" + user_type
				+ ", is_enabled=" + is_enabled + ", os_type=" + os_type + ", device_name=" + device_name + ", device_uuid=" + device_uuid
				+ ", is_auto_bluetooth=" + is_auto_bluetooth + ", is_on_alarm=" + is_on_alarm + ", is_support_ble=" + is_support_ble
				+ ", img_server_host=" + img_server_host + ", profile_img=" + profile_img + ", push_token=" + push_token + ", push_token_owner="
				+ push_token_owner + ", push_token_staff=" + push_token_staff + ", user_id_orig=" + user_id_orig + ", is_original_user="
				+ is_original_user + ", version=" + version + ", is_first=" + is_first + "]";
	}

}
