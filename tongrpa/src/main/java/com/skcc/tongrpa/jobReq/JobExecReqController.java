package com.skcc.tongrpa.jobReq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.job.JobModel;
import com.skcc.tongrpa.job.JobService;
import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class JobExecReqController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private JobExecReqService jobReqService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private agentService agService;
    
	private static final Logger logger = LoggerFactory.getLogger(JobExecReqController.class);
	
	
	   /*   Batch Job실행 요청    */
		@RequestMapping("/BatchjobExecReq")
		public @ResponseBody HashMap<String,Object> BatchjobExecReq(@RequestParam(value="jobId") String jobId ,
				@RequestParam(value="batchId") String batchId ,
				HttpServletRequest request) {

			HashMap<String,Object> result=new HashMap<String,Object>();
			
			// JOB 정보조회
			JobModel jm=jobService.getJobInfo(jobId);
			
			if(jm!=null) {// JOB정보확인 
				 
				logger.debug("BatchjobExecReq started...");;
				// 가용 AGent 조회
			     List<agentModel> amList=agService.getIdleAgentList();
			     
			     if(amList!=null && amList.size()>0) {//가용 Agent있음
			    	  String idleAgentId=amList.get(0).getAgent_id();
			    	  
			    	  // DB 등록
			    	  String  JobExecReqId=jobReqService.insertJobExecReq(idleAgentId, jobId, batchId);
			    	  
			    	  // MQ 등록 
			    	  
			    	  boolean execRlt=mqSenderService.jobExecRegMQ(JobExecReqId, idleAgentId, jm);
			    	  result.put("result", execRlt);
			    	  
			    	  //JobExecResult?jobExecReqId=?
			    	  
			    	  String resultCheckUrl="/JobExecResult?jobExecReqId="+JobExecReqId;
			    	  
			    	  result.put("resultCheckUrl", resultCheckUrl);
					logger.debug("BatchjobExecReq end...");;
			    	  
			     }else {//  전체 agent  다운됨
			    	 
			    	 result.put("result", false);
			    	 result.put("cause", "alldown");
			     }
			}else {
				result.put("result", false);
			}
			
			
			
			return result;
		}
		  
	
    /*  Job실행 요청  정보 목록 조회   */
	@RequestMapping("/jobExecReqList")
	public @ResponseBody List<JobExecReqModel> getJobList(
			@RequestParam(value="agentId", defaultValue="") String agentId,
			@RequestParam(value="jobId", defaultValue="") String jobId,
			@RequestParam(value="jobExecReqId", defaultValue="") String jobExecReqId,
			@RequestParam(value="staDt" , defaultValue="") String staDt,
			@RequestParam(value="endDt" , defaultValue="") String endDt,			
			@RequestParam(value="jobStatus", defaultValue="") String jobStatus) {
		
		
		String staDtm="";
		String endDtm="";
		if(staDt.length()==10 &&  endDt.length()==10) {
			staDtm=staDt+" 00:00:00";
			endDtm=endDt+" 23:59:59";
		}
		
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		logger.debug("today :" + today);
		if(staDt.length()==0) {
			staDtm=today+" 00:00:00";
		}
		if(endDt.length()==0) {
			endDtm=today+" 23:59:59";
		}
		
		List<JobExecReqModel> list=  jobReqService.getJobExecReqList( agentId, jobId, jobStatus,jobExecReqId,staDtm,endDtm);

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
			ex.printStackTrace();
		}
		resultMap.put("result", jerm);
		return resultMap ;
	}

	/* Job 정보  수정 */
	@RequestMapping("updateJobExecReqInfo")
	public @ResponseBody HashMap<String, Object>  updateJob(
			@RequestParam(value="jobExecReqId") String jobExecReqId,
			@RequestParam(value="jobRltData") String jobRltData,
			@RequestParam(value="jobStatus") String jobStatus) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= jobReqService.updateJobExecReqInfo(  jobExecReqId, jobStatus ,jobRltData);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}


    
}
