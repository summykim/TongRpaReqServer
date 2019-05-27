package com.skcc.tongrpa.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobScheduleService {

	@Autowired
	private JobScheduleDao jobSchdao;


	private static final Logger logger = LoggerFactory.getLogger(JobScheduleService.class);

	/*
	 * Job 정보 조회
	 */
	public JobScheduleModel getJobScheduleInfo(String id){



		return jobSchdao.getJobScheduleInfo(id);

	}


	/*
	 * Job 전체  목록  조회
	 */
	public List<JobScheduleModel> getJobScheduleList (String id,String jobId) {

		HashMap<String, String> hm =new HashMap<String, String>();
		hm.put("id", id);
		hm.put("jobId", jobId);
		return jobSchdao.getJobScheduleList(hm);

	}
	/*
	 * Job 추가 
	 */
	public int  insertJobSchedule(String jobId,String cron,String regUser) {
		


		return jobSchdao.insertJobSchedule( jobId, cron, regUser);
	}
	/*
	 * Job 정보  수정
	 */
	public int  updateJobScheduleInfo(String id,String cron,String regUser) {
		return jobSchdao.updateJobScheduleInfo(  id ,cron, regUser);
	}


	/*
	 * Job 정보  삭제
	 */
	public int  deleteJobSchedule (String id) {

		return jobSchdao.deleteJobSchedule( id);
	}

}
