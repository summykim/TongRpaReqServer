package com.skcc.tongrpa.chbotgw;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping("/checkUser")
	public @ResponseBody HashMap<String,Object> getSearchUser(
			@RequestParam(value="chbotKey", defaultValue="") String chbotKey) {

		HashMap<String,Object> result=new HashMap<String,Object>();
		
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("chbotKey", chbotKey);
		
		UserModel um=userService.getUserInfo(hm);
		if(um!=null) {
			result.put("result", true);
			result.put("userName", um.getUser_nm());
		}else {
			result.put("result", false);
		}
		
		return result;
	}

	/* 사용자  챗봇키 정보  업데이트  */
	@RequestMapping("userSignUp")
	public @ResponseBody HashMap<String, Object>  updateUserChBotKey(
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userEmail") String userEmail) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			
			resunt_cnt= userService.updateUserChBotKey(userEmail, chbotKey);
			if(resunt_cnt>0) {
				HashMap<String,String> hm=new HashMap<String,String>();
				hm.put("chbotKey", chbotKey);
				
				UserModel um=userService.getUserInfo(hm);
				resultMap.put("result", true);
				resultMap.put("userName", um.getUser_nm());
			}
			else resultMap.put("result", false);
		}catch(Exception ex) {
			ex.printStackTrace();
			resultMap.put("result", false);
		}
		
		return resultMap ;
	}
	/* 사용자  챗봇키 정보  업데이트  */
	@RequestMapping("userDrop")
	public @ResponseBody HashMap<String, Object>  userDrop(
			@RequestParam(value="chbotKey") String chbotKey) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			
			HashMap<String,String> hm=new HashMap<String,String>();
			hm.put("chbotKey", chbotKey);			
			UserModel um=userService.getUserInfo(hm);		
			if(um !=null) {
				resunt_cnt= userService.updateUserChBotKey(um.getUser_email(), "");
				if(resunt_cnt>0)
					resultMap.put("result", true);
				else resultMap.put("result", false);
			}else {
				resultMap.put("result", false);
			}


		}catch(Exception ex) {
			ex.printStackTrace();
			resultMap.put("result", false);
		}
		
		return resultMap ;
	}
    /*   Job검색 요청    */
	@RequestMapping("/searchJobList")
	public @ResponseBody HashMap<String,Object> searchJobList(@RequestParam(value="searchText", defaultValue="") String searchText,
			@RequestParam(value="chbotKey") String chbotKey)  {

		HashMap<String,Object> result=new HashMap<String,Object>();
		
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("chbotKey", chbotKey);
		
		UserModel um=userService.getUserInfo(hm);
		
		List<JobModel> jmList=jobService.getJobList(searchText,um.getUser_id());
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
		public @ResponseBody HashMap<String,Object> jobExecReq(@RequestParam(value="jobId") String jobId ,HttpServletRequest request) {

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
			    	  
			    	  //JobExecResult?jobExecReqId=?
			    	  
			    	  String resultCheckUrl="/JobExecResult?jobExecReqId="+JobExecReqId;
			    	  
			    	  result.put("resultCheckUrl", resultCheckUrl);
			     }else {//  전체 agent  다운됨
			    	 
			    	 result.put("result", false);
			    	 result.put("cause", "alldown");
			     }
			}else {
				result.put("result", false);
			}
			
			
			
			return result;
		}
		  


}
