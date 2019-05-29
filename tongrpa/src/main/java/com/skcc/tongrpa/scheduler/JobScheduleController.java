package com.skcc.tongrpa.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobScheduleController {


	
	@Autowired
	private JobScheduleService jobSchInfoService;
	
	@Autowired
	DynamicScheduleService  dynaSchService;
    
    /*  scheduler start   */
	@RequestMapping("/StartJobScheduler")
	public @ResponseBody HashMap<String,Object >  StartJobScheduler(@RequestParam(value="id" , defaultValue="") String id) {
		
         // 전체 스케쥴러 조회
		 List<JobScheduleModel> schList=jobSchInfoService.getJobScheduleList(id, "","");
		 int resultCnt=0;
		 Map<String,Object> result_data=new  HashMap<String,Object>();
		 for(int i=0; i< schList.size();i++) {
			 JobScheduleModel jsch= schList.get(i);
			 boolean result=dynaSchService.addTaskToScheduler(jsch.getId(),jsch.getJob_id(),jsch.getCron());
			 if(result) {
				 resultCnt++;//성공
			 }
			 result_data.put(jsch.getId(), result);
		 }
        
        
		 HashMap<String,Object > resultMap=new HashMap<String,Object>();
		 resultMap.put("result_cnt", resultCnt);
		 resultMap.put("result_data", result_data);
		return   resultMap;

	}
	
    /*  scheduler start   */
	@RequestMapping("/StopJobScheduler")
	public @ResponseBody HashMap<String,Object >  StopJobScheduler(@RequestParam(value="id" , defaultValue="") String id) {
		
         //  스케쥴러 조회
		 List<JobScheduleModel> schList=jobSchInfoService.getJobScheduleList(id, "","");
		 int resultCnt=0;
		 Map<String,Object> result_data=new  HashMap<String,Object>();
		 for(int i=0; i< schList.size();i++) {
			 JobScheduleModel jsch= schList.get(i);
			 boolean  result=dynaSchService.removeTaskFromScheduler(jsch.getId());
			 if(result) resultCnt++;//삭제성공
			 result_data.put(jsch.getId(), result);
		 }
        
        
		 HashMap<String,Object > resultMap=new HashMap<String,Object>();
		 resultMap.put("result_cnt", resultCnt);
		 resultMap.put("result_data", result_data);
		
		return   resultMap;

	}	
    /*  scheduler start   */
	@RequestMapping("/StatusJobScheduler")
	public @ResponseBody HashMap<String,Object >  StatusJobScheduler(@RequestParam(value="id" , defaultValue="") String id) {
		
         // 전체 스케쥴러 조회
		 List<JobScheduleModel> schList=jobSchInfoService.getJobScheduleList(id, "","");
		 int resultCnt=0;
		 Map<String,Object> result_data=new  HashMap<String,Object>();
		 for(int i=0; i< schList.size();i++) {
			 JobScheduleModel jsch= schList.get(i);
			 boolean result=dynaSchService.getTaskFromScheduler(jsch.getId());
			 if(result) {
				 resultCnt++;//성공
			 }
			 result_data.put(jsch.getId(), result);
		 }
        
        
		 HashMap<String,Object > resultMap=new HashMap<String,Object>();
		 resultMap.put("result_cnt", resultCnt);
		 resultMap.put("result_data", result_data);
		return   resultMap;

	}  
    /* scheduler  정보조회   */
	@RequestMapping("/jobScheduleList")
	public @ResponseBody List<JobScheduleModel> jobId(@RequestParam(value="id" , defaultValue="") String id,
			@RequestParam(value="jobId" , defaultValue="") String jobId,
			@RequestParam(value="batchNm" , defaultValue="") String batchNm) {

		List<JobScheduleModel> list=  jobSchInfoService.getJobScheduleList(id,jobId,batchNm);

		return   list;

	}

	/* Job scheduler정보 조회 */
	@RequestMapping("getJobScheduleInfo")
	public @ResponseBody JobScheduleModel  searchJob(@RequestParam(value="id") String id) {
		JobScheduleModel vo= jobSchInfoService.getJobScheduleInfo(id);
		return vo ;
	}


	/* Job 정보  추가 */
	@RequestMapping("insertJobSchedule")
	public @ResponseBody HashMap<String, Object>  inserJob(
			@RequestParam(value="jobId") String jobId,
			@RequestParam(value="cron") String cron,
			@RequestParam(value="batchNm") String batchNm,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= jobSchInfoService.insertJobSchedule(  jobId, cron, regUser,batchNm);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Job scheduler정보  수정 */
	@RequestMapping("updateJobScheduleInfo")
	public @ResponseBody HashMap<String, Object>  updateScheduleJob(@RequestParam(value="id") String id,
			@RequestParam(value="cron") String cron,
			@RequestParam(value="batchNm") String batchNm,
			@RequestParam(value="regUser") String regUser ) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= jobSchInfoService.updateJobScheduleInfo(id,  cron, regUser,batchNm);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Job scheduler정보  삭제 */
	@RequestMapping("deleteJobSchedule")
	public @ResponseBody HashMap<String, Object>  deleteJobSchedule(@RequestParam(value="id") String id ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {		
			resunt_cnt= jobSchInfoService.deleteJobSchedule(id);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}               

    
}
