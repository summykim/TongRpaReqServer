package com.skcc.tongrpa.jobReq;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class JobExecReqController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private JobExecReqService jobReqService;
    
    /*  Job실행 요청  정보 목록 조회   */
	@RequestMapping("/jobExecReqList")
	public @ResponseBody List<JobExecReqModel> getJobList(
			@RequestParam(value="agentId") String agentId,
			@RequestParam(value="jobId") String jobId,
			@RequestParam(value="jobExecReqId") String jobExecReqId,
			@RequestParam(value="jobStatus") String jobStatus) {

		List<JobExecReqModel> list=  jobReqService.getJobExecReqList( agentId, jobId, jobStatus,jobExecReqId);

		return   list;

	}

	/* Job 정보 조회 */
	@RequestMapping("searchjobExecReq")
	public @ResponseBody JobExecReqModel  searchJob(@RequestParam(value="jobExecReqId") String jobExecReqId) {
		JobExecReqModel vo= jobReqService.getJobExecReqInfo(jobExecReqId);
		return vo ;
	}


	/* Job 정보  추가 */
	@RequestMapping("insertJobExecReq")
	public @ResponseBody HashMap<String, Object>  inserJob(
			@RequestParam(value="agentId") String agentId,
			@RequestParam(value="jobId") String jobId,
			@RequestParam(value="regUser") String regUser) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		String  JobExecReqId="";
		JobExecReqModel jerm=null;
		try {
			JobExecReqId= jobReqService.insertJobExecReq(agentId, jobId,  regUser);
			jerm =jobReqService.getJobExecReqInfo(JobExecReqId);

		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result", jerm);
		return resultMap ;
	}

	/* Job 정보  수정 */
	@RequestMapping("updateJobExecReqInfo")
	public @ResponseBody HashMap<String, Object>  updateJob(@RequestParam(value="jobExecReqId") String jobExecReqId,
			@RequestParam(value="jobStatus") String jobStatus) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= jobReqService.updateJobExecReqInfo(  jobExecReqId, jobStatus);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}


    
}
