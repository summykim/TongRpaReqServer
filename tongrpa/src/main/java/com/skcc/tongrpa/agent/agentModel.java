package com.skcc.tongrpa.agent;
/*
 * agent 정보
 */
public class agentModel {
	private String  agent_id ;  //agent 아이디
	private String  agent_nm;  //agent 명
	private String  agent_desc;  //agent상세설명
	private String  agent_uid ;  //agent pc mac or ip
	private String  agent_status;  //상태 (idle/busy/down)
	private String  reg_user ;  // 등록자 
	private String  reg_dtm ;  //등록일,
	private String  upd_dtm ;  //업데이트일시
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getAgent_nm() {
		return agent_nm;
	}
	public void setAgent_nm(String agent_nm) {
		this.agent_nm = agent_nm;
	}
	public String getAgent_desc() {
		return agent_desc;
	}
	public void setAgent_desc(String agent_desc) {
		this.agent_desc = agent_desc;
	}
	public String getAgent_uid() {
		return agent_uid;
	}
	public void setAgent_uid(String agent_uid) {
		this.agent_uid = agent_uid;
	}
	public String getAgent_status() {
		return agent_status;
	}
	public void setAgent_status(String agent_status) {
		this.agent_status = agent_status;
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