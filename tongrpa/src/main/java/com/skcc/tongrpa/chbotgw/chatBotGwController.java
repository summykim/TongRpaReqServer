package com.skcc.tongrpa.chbotgw;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.job.JobModel;
import com.skcc.tongrpa.job.JobService;
import com.skcc.tongrpa.jobReq.JobExecReqModel;
import com.skcc.tongrpa.jobReq.JobExecReqService;
import com.skcc.tongrpa.mq.MqSenderService;
import com.skcc.tongrpa.user.UserModel;
import com.skcc.tongrpa.user.UserService;

@RestController
public class chatBotGwController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private JobExecReqService jobReqService;

	@Autowired
	private JobService jobService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private agentService agService;
    
    /*   사용자 인증    */
	@RequestMapping("/getSearchUser")
	public @ResponseBody HashMap<String,String> getSearchUser(@RequestParam(value="userId") String userId) {

		HashMap<String,String> result=new HashMap<String,String>();
		
		UserModel um=userService.getUserInfo(userId);
		if(um!=null) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}


    /*   Job검색 요청    */
	@RequestMapping("/searchJobList")
	public @ResponseBody HashMap<String,Object> searchJobList(@RequestParam(value="searchText") String searchText,
			@RequestParam(value="authUser") String authUser)  {

		HashMap<String,Object> result=new HashMap<String,Object>();
		
		List<JobModel> jmList=jobService.getJobList(searchText,authUser);
		if(jmList!=null) {
			result.put("result", "success");
			result.put("jobList", jmList);
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}
 
	
	
	   /*   Job실행 요청    */
		@RequestMapping("/jobExecReq")
		public @ResponseBody HashMap<String,Object> jobExecReq(@RequestParam(value="jobId") String jobId) {

			HashMap<String,Object> result=new HashMap<String,Object>();
			
			// JOB 정보조회
			JobModel jm=jobService.getJobInfo(jobId);
			
			if(jm!=null) {// JOB정보확인 
				 
				// 가용 AGent 조회
			     List<agentModel> amList=agService.getIdleAgentList();
			     
			     if(amList!=null) {//가용 Agent있음
			    	  String idleAgentId=amList.get(0).getAgent_id();
			    	  
			    	  // DB 등록
			    	  String  JobExecReqId=jobReqService.insertJobExecReq(idleAgentId, jobId, jm.getAuth_user());
			    	  
			    	  // MQ 등록 
			    	  
			    	  boolean execRlt=mqSenderService.jobExecRegMQ(JobExecReqId, idleAgentId, jm);
			    	  result.put("result", execRlt);
			     }else {//  전체 agent  다운됨
			    	 
			    	 
			     }
			}
			
			
			
			return result;
		}
		  
	    /*   Job실행결과 테스트용   */
		@RequestMapping("/jobExecReqResult")
		public @ResponseBody HashMap<String,Object> jobExecReqResult(
				@RequestParam(value="jobExecReqId") String jobExecReqId,
				@RequestParam(value="agentStaus") String agentStaus,
				@RequestParam(value="execStaus") String execStaus) {
			

			HashMap<String,Object> result=new HashMap<String,Object>();
			
			// JOB실행 정보조회
			JobExecReqModel jeRM=jobReqService.getJobExecReqInfo(jobExecReqId);
			
			boolean execRlt=false;
			if(jeRM!=null) {
				execRlt=mqSenderService.agentExecResponseMqReturn(jeRM.getExec_req_id(),jeRM.getAgent_id(), agentStaus, execStaus);
			}
			
			result.put("result", execRlt);
			
			return result;
		}
		
	    /*   Agent상태 결과 테스트용   */
		@RequestMapping("/agentHealthReqResult")
		public @ResponseBody HashMap<String,Object> agentHealthReqResult(
				@RequestParam(value="agentId") String agentId,
				@RequestParam(value="status") String status) {
			

			HashMap<String,Object> result=new HashMap<String,Object>();
			
			
			boolean execRlt=false;

			execRlt=mqSenderService.agentHealthMqReturn(agentId, status);

			result.put("result", execRlt);
			
			return result;
		}		
}
