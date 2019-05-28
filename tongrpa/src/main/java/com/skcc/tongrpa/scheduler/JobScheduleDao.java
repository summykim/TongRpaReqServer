package com.skcc.tongrpa.scheduler;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 *  Job 정보 관리 DAO
 */
@Mapper
public interface JobScheduleDao {
	public List<JobScheduleModel> getJobScheduleList(HashMap<String, String> hm);
	public JobScheduleModel getJobScheduleInfo(String id);
	public int insertJobSchedule(String jobId,String cron,String regUser,String batchNm);
	public int updateJobScheduleInfo(String id,String cron,String regUser,String batchNm);
	public int deleteJobSchedule(String id);
}

