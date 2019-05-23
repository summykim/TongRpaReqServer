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
	public @ResponseBody HashMap<String,String> getSearchUser(
			@RequestParam(value="userId", defaultValue="") String userId,
			@RequestParam(value="chbotKey", defaultValue="") String chbotKey,
			@RequestParam(value="userPhone") String userPhone) {

		HashMap<String,String> result=new HashMap<String,String>();
		
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("userId", userId);
		hm.put("chbotKey", chbotKey);
		hm.put("userPhone", userPhone);
		
		UserModel um=userService.getUserInfo(hm);
		if(um!=null) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
	}

	/* 사용자  챗봇키 정보  업데이트  */
	@RequestMapping("updateChBotKey")
	public @ResponseBody HashMap<String, Object>  updateUserChBotKey(
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userPhone") String userPhone) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= userService.updateUserChBotKey(userPhone, chbotKey);
			if(resunt_cnt>0)resultMap.put("result", "success");
		}catch(Exception ex) {
			ex.printStackTrace();
			resultMap.put("result", "fail");
		}
		
		return resultMap ;
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
			     
			     if(amList!=null && amList.size()>0) {//가용 Agent있음
			    	  String idleAgentId=amList.get(0).getAgent_id();
			    	  
			    	  // DB 등록
			    	  String  JobExecReqId=jobReqService.insertJobExecReq(idleAgentId, jobId, jm.getAuth_user());
			    	  
			    	  // MQ 등록 
			    	  
			    	  boolean execRlt=mqSenderService.jobExecRegMQ(JobExecReqId, idleAgentId, jm);
			    	  result.put("result", execRlt);
			     }else {//  전체 agent  다운됨
			    	 
			    	 result.put("result", false);
			    	 result.put("cause", "alldown");
			     }
			}
			
			
			
			return result;
		}
		  


}
