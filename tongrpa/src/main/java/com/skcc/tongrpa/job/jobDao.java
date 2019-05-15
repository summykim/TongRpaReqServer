package com.skcc.tongrpa.job;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.skcc.tongrpa.sample.MapDto;

/*
 *  Job 정보 관리 DAO
 */
@Mapper
public interface jobDao {
	public List<jobModel> getJobList();
	public jobModel getJobInfo(String jobId);
	public int insertJob(String jobId,String jobNm,String jobDesc,String jobData,String jobSeq,String authUser,String regUser);
	public int updateJobInfo(String jobId,String jobNm,String jobDesc,String jobData);
	public int deleteJob(String jobId);
}

