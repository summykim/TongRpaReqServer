package com.skcc.tongrpa.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

	@Autowired
	private JobDao jobdao;


	private static final Logger logger = LoggerFactory.getLogger(JobService.class);

	/*
	 * Job 정보 조회
	 */
	public JobModel getJobInfo(String jobId){



		return jobdao.getJobInfo(jobId);

	}


	/*
	 * Job 전체  목록  조회
	 */
	public List<JobModel> getJobList (String searchText,String authUser) {

		HashMap<String, String> hm =new HashMap<String, String>();
		hm.put("searchText", searchText);
		hm.put("authUser", authUser);
		return jobdao.getJobList(hm);

	}
	/*
	 * Job 추가 
	 */
	public int  insertJob(String jobNm,String jobDesc,String jobData,String jobTyp,String authUser,String regUser,String jobTmout) {
		
		String curTimekey=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		JobModel jobSeqInfo=jobdao.getJobSeqByUser(authUser);
		String jobId="";
		int jobSeq=1;
		if(jobSeqInfo==null) {
			jobId=authUser+String.format("%03d",jobSeq)+curTimekey;
		}else {
			jobSeq=jobSeqInfo.getJob_seq();
			jobId=authUser+String.format("%03d",jobSeq)+curTimekey;
		}
		return jobdao.insertJob(jobId ,jobNm, jobDesc, jobData,jobTyp,jobSeq,  authUser, regUser,jobTmout);
	}
	/*
	 * Job 정보  수정
	 */
	public int  updateJobInfo(String jobId,String jobNm,String jobDesc,String jobData,String jobTyp,String jobTmout) {
		return jobdao.updateJobInfo(  jobId, jobNm, jobDesc, jobData,jobTyp,jobTmout);
	}


	/*
	 * Job 정보  삭제
	 */
	public int  deleteJob (String agentId) {

		return jobdao.deleteJob( agentId);
	}

}
