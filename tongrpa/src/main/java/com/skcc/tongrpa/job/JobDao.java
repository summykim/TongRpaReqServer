package com.skcc.tongrpa.job;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 *  Job 정보 관리 DAO
 */
@Mapper
public interface JobDao {
	public List<JobModel> getJobList(HashMap<String, String> hm);
	public JobModel getJobInfo(String jobId);
	public JobModel getJobSeqByUser(String authUser);
	public int insertJob(String jobId,String jobNm,String jobDesc,String jobData,String jobTyp,int jobSeq,String authUser,String regUser);
	public int updateJobInfo(String jobId,String jobNm,String jobDesc,String jobData,String jobTyp);
	public int deleteJob(String jobId);
}

