package com.skcc.tongrpa.scheduler;

import java.util.HashMap;
import java.util.List;

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
		 List<JobScheduleModel> schList=jobSchInfoService.getJobScheduleList(id, "");
		 int resultCnt=0;
		 for(int i=0; i< schList.size();i++) {
			 JobScheduleModel jsch= schList.get(i);
			 dynaSchService.addTaskToScheduler(jsch.getId(),jsch.getJob_id(),jsch.getCron());
			 resultCnt++;
		 }
        
        
		 HashMap<String,Object > resultMap=new HashMap<String,Object>();
		 resultMap.put("result_cnt", resultCnt);
		
		return   resultMap;

	}
	
    /*  scheduler start   */
	@RequestMapping("/StopJobScheduler")
	public @ResponseBody HashMap<String,Object >  StopJobScheduler(@RequestParam(value="id" , defaultValue="") String id) {
		
         // 전체 스케쥴러 조회
		 List<JobScheduleModel> schList=jobSchInfoService.getJobScheduleList(id, "");
		 int resultCnt=0;
		 for(int i=0; i< schList.size();i++) {
			 JobScheduleModel jsch= schList.get(i);
			 dynaSchService.removeTaskFromScheduler(jsch.getId());
			 resultCnt++;
		 }
        
        
		 HashMap<String,Object > resultMap=new HashMap<String,Object>();
		 resultMap.put("result_cnt", resultCnt);
		
		return   resultMap;

	}	
    
    /* scheduler  정보조회   */
	@RequestMapping("/jobScheduleList")
	public @ResponseBody List<JobScheduleModel> jobId(@RequestParam(value="id" , defaultValue="") String id,
			@RequestParam(value="jobId" , defaultValue="") String jobId) {

		List<JobScheduleModel> list=  jobSchInfoService.getJobScheduleList(id,jobId);

		return   list;

	}

	/* Job scheduler정보 조회 */
	@RequestMapping("searchJobSchedule")
	public @ResponseBody JobScheduleModel  searchJob(@RequestParam(value="id") String id) {
		JobScheduleModel vo= jobSchInfoService.getJobScheduleInfo(id);
		return vo ;
	}


	/* Job 정보  추가 */
	@RequestMapping("insertJobSchedule")
	public @ResponseBody HashMap<String, Object>  inserJob(
			@RequestParam(value="jobId") String jobId,
			@RequestParam(value="cron") String cron,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= jobSchInfoService.insertJobSchedule(  jobId, cron, regUser);

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
			@RequestParam(value="regUser") String regUser ) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= jobSchInfoService.updateJobScheduleInfo(id,  cron, regUser);
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
