package com.skcc.tongrpa.scheduler;
/*
 * JOB 정보
 */
public class JobScheduleModel {
	private String  id ; //scheduer id
	private String  job_id; //job 아이디',
	private String  cron; //cron 설정 ,
	private String  batch_nm ;// batch명
	private boolean  batch_status ;// batch상태
	private String  reg_user ;  // 등록자 
	private String  reg_dtm ;  //등록일,
	private String  upd_dtm ;  //업데이트일시
	
	

	public boolean getBatch_status() {
		return batch_status;
	}
	public void setBatch_status(boolean batch_status) {
		this.batch_status = batch_status;
	}
	public String getBatch_nm() {
		return batch_nm;
	}
	public void setBatch_nm(String batch_nm) {
		this.batch_nm = batch_nm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
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