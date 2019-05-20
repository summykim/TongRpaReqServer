package com.skcc.tongrpa.mq;
/*
 *  Mq 수신정보  정
 */
public class MqRecvModel {
	private String  res_typ ;  //수신구분
	private String  agent_id;  //agent 아이디
	private String  exec_req_id;  //agent상세설명
	private String  agent_status;  //Agent 상태 (idle/busy/down)
	private String  job_status ;  // Job 상태 

	public String getRes_typ() {
		return res_typ;
	}
	public void setRes_typ(String res_type) {
		this.res_typ = res_type;
	}
	public String getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	public String getExec_req_id() {
		return exec_req_id;
	}
	public void setExec_req_id(String exec_req_id) {
		this.exec_req_id = exec_req_id;
	}
	public String getAgent_status() {
		return agent_status;
	}
	public void setAgent_status(String agent_status) {
		this.agent_status = agent_status;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

}