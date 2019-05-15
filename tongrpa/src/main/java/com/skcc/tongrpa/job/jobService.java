package com.skcc.tongrpa.job;

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
public class jobService {

	@Autowired
	private jobDao jobdao;


	private static final Logger logger = LoggerFactory.getLogger(jobService.class);

	/*
	 * Job 정보 조회
	 */
	public jobModel getJobInfo(String agentId){



		return jobdao.getJobInfo(agentId);

	}


	/*
	 * Job 전체  목록  조회
	 */
	public List<jobModel> getJobList () {


		return jobdao.getJobList();

	}
	/*
	 * Job 추가 
	 */
	public int  insertJob(String jobId,String jobNm,String jobDesc,String jobData,String jobSeq,String authUser,String regUser) {
		return jobdao.insertJob(  jobId, jobNm, jobDesc, jobData, jobSeq, authUser, regUser);
	}
	/*
	 * Job 정보  수정
	 */
	public int  updateJobInfo(String jobId,String jobNm,String jobDesc,String jobData) {
		return jobdao.updateJobInfo(  jobId, jobNm, jobDesc, jobData);
	}


	/*
	 * Job 정보  삭제
	 */
	public int  deleteJob (String agentId) {

		return jobdao.deleteJob( agentId);
	}

}
