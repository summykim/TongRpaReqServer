package com.skcc.tongrpa.jobReq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.mq.MqRecvModel;

@Service
public class JobExecReqService {

	@Autowired
	private JobExecReqDao jobReqdao;


	private static final Logger logger = LoggerFactory.getLogger(JobExecReqService.class);

	/*
	 * Job 정보 조회
	 */
	public JobExecReqModel getJobExecReqInfo(String jobReqId){



		return jobReqdao.getJobExecReqInfo(jobReqId);

	}


	/*
	 * Job 전체  목록  조회
	 */
	public List<JobExecReqModel> getJobExecReqList (String agentId,String jobId,String jobStatus,String jobExecReqId) {

		return jobReqdao.getJobExecReqList(agentId,jobId,jobStatus,jobExecReqId);

	}
	/*
	 * Job 추가 
	 */
	public  String   insertJobExecReq(String agentId,String jobId,String regUser){
		String resultKey="";
		String curTimekey=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

		String jobExecReqId=agentId+curTimekey;
		logger.debug("jobExecReqId : ",jobExecReqId);
		int result =jobReqdao.insertJobExecReq(  jobExecReqId, agentId, jobId,  regUser);
		
		if(result>0) {
			resultKey= jobExecReqId;
		}
		
		return resultKey;
		
	}
	/*
	 * Job 정보  수정
	 */
	public int  updateJobExecReqInfo(String jobExecReqId,String jobStatus,String rltData) {
		// 결과값의 성공여부  확인
		String  rltStatus="fail";
		if(rltData!=null && rltData.length()>0) {
			 rltStatus="success";
		}
		return jobReqdao.updateJobExecReqInfo(  jobExecReqId, jobStatus,rltStatus, rltData );
	}

	/*
	 * Job 상태 정보  수정
	 */
	public int  updateJobExecReqStatus(String jobExecReqId,String jobStatus) {
		return jobReqdao.updateJobExecReqStatus( jobExecReqId,jobStatus);
	}
}
