package com.skcc.tongrpa.job;
/*
 * JOB 정보
 */
public class JobModel {
	private String  job_id; //job 아이디',
	private String  job_nm; //job 명',
	private String  job_desc; //job상세설명',
	private String  job_data; //job 실행 데이터(스크립트소스, url)',
	private String  job_typ; //job구분: RST/PY',	
	private int  job_seq; //사용자별job seq',
	private String  auth_user; // JOB 소유자
	private String  reg_user ;  // 등록자 
	private String  reg_dtm ;  //등록일,
	private String  upd_dtm ;  //업데이트일시
	private String  job_tmout ;  //JOB 실행 타임아웃값(초단위)

	public String getJob_tmout() {
		return job_tmout;
	}
	public void setJob_tmout(String job_tmout) {
		this.job_tmout = job_tmout;
	}
	public int getJob_seq() {
		return job_seq;
	}
	public void setJob_seq(int job_seq) {
		this.job_seq = job_seq;
	}
	public String getJob_typ() {
		return job_typ;
	}
	public void setJob_typ(String job_typ) {
		this.job_typ = job_typ;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_nm() {
		return job_nm;
	}
	public void setJob_nm(String job_nm) {
		this.job_nm = job_nm;
	}
	public String getJob_desc() {
		return job_desc;
	}
	public void setJob_desc(String job_desc) {
		this.job_desc = job_desc;
	}
	public String getJob_data() {
		return job_data;
	}
	public void setJob_data(String job_data) {
		this.job_data = job_data;
	}

	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
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