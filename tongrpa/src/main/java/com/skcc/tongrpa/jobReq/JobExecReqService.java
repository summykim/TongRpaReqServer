package com.skcc.tongrpa.jobReq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.sample.MapMapper;

@Service
public class JobExecReqService {

	@Autowired
	private JobExecReqDao jobReqdao;


	private static final Logger logger = LoggerFactory.getLogger(JobExecReqService.class);

	/*
	 * Job 정보 조회
	 */
	public JobExecReqModel getJobExecReqInfo(String agentId){



		return jobReqdao.getJobExecReqInfo(agentId);

	}


	/*
	 * Job 전체  목록  조회
	 */
	public List<JobExecReqModel> getJobExecReqList () {

		return jobReqdao.getJobExecReqList();

	}
	/*
	 * Job 추가 
	 */
	public int  insertJobExecReq(String jobExecReqId,String agentId,String jobId,String regUser){
		return jobReqdao.insertJobExecReq(  jobExecReqId, agentId, jobId,  regUser);
	}
	/*
	 * Job 정보  수정
	 */
	public int  updateJobExecReqInfo(String jobExecReqId,String jobStatus) {
		return jobReqdao.updateJobExecReqInfo(  jobExecReqId, jobStatus);
	}


}
