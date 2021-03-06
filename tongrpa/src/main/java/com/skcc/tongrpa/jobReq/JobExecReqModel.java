package com.skcc.tongrpa.jobReq;
/*
 * JOB실행요청 정보
 */
public class JobExecReqModel {
	private String  exec_req_id;//job실행id\nyyyymmddhh24misssss+jobid',
	private String  agent_id;// Agent ID
	private String  job_id;// JOB ID,
	private String  job_status;// JOB 상태 'req' comment 'req/ ing / cmp',
	private String  reg_user ;  // 등록자 
	private String  reg_dtm ;  //등록일,
	private String  upd_dtm ;  //업데이트일시
	private String  agent_status ;  //agent상태
	private String  rlt_data; // Job실행 결과 데이터
	private String  rlt_status; // Job실행 결과 상태
	
	public String getRlt_status() {
		return rlt_status;
	}
	public void setRlt_status(String rlt_status) {
		this.rlt_status = rlt_status;
	}
	public String getRlt_data() {
		return rlt_data;
	}
	public void setRlt_data(String rlt_data) {
		this.rlt_data = rlt_data;
	}
	public String getAgent_status() {
		return agent_status;
	}
	public void setAgent_status(String agent_status) {
		this.agent_status = agent_status;
	}
	public String getExec_req_id() {
		return exec_req_id;
	}
	public void setExec_req_id(String exec_req_id) {
		this.exec_req_id = exec_req_id;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
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
