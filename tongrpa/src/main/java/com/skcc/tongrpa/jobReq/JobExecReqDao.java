package com.skcc.tongrpa.jobReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 *  Job실행요청 정보 관리 DAO
 */
@Mapper
public interface JobExecReqDao {
	public List<JobExecReqModel> getJobExecReqList(String agentId,String jobId,String jobStatus);
	public JobExecReqModel getJobExecReqInfo(String jobExecReqId);
	public int insertJobExecReq(String jobExecReqId,String agentId,String jobId,String regUser);
	public int updateJobExecReqInfo(String jobExecReqId,String jobStatus);
}

