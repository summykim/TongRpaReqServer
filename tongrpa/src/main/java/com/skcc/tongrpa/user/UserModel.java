package com.skcc.tongrpa.user;
/*
 * 사용자정보
 */
public class UserModel {
	private String user_id   ;
	private String user_nm   ;
	private String chbot_key ;
	private String user_typ  ;
	private String reg_user  ;
	private String reg_dtm   ;
	private String upd_dtm   ;
	private String user_pwd  ;
	private String user_phone  ;	
	private String user_email  ;	
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getChbot_key() {
		return chbot_key;
	}
	public void setChbot_key(String chbot_key) {
		this.chbot_key = chbot_key;
	}
	public String getUser_typ() {
		return user_typ;
	}
	public void setUser_typ(String user_typ) {
		this.user_typ = user_typ;
	}
	public String getReg_user() {
		return reg_user;
	}
	public void setReg_user(String reg_user) {
		this.reg_user = reg_user;
	}
	public String getReg_dtm() {
		return reg_dtm;
	}
	public void setReg_dtm(String reg_dtm) {
		this.reg_dtm = reg_dtm;
	}
	public String getUpd_dtm() {
		return upd_dtm;
	}
	public void setUpd_dtm(String upd_dtm) {
		this.upd_dtm = upd_dtm;
	}

}