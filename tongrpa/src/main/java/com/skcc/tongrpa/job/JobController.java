package com.skcc.tongrpa.job;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class JobController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private JobService jobInfoService;
    
    /* agent 정보조   */
	@RequestMapping("/jobList")
	public @ResponseBody List<JobModel> getJobList(@RequestParam(value="searchText") String searchText,
			@RequestParam(value="authUser") String authUser) {

		List<JobModel> list=  jobInfoService.getJobList(searchText,authUser);

		return   list;

	}

	/* Job 정보 조회 */
	@RequestMapping("searchJob")
	public @ResponseBody JobModel  searchJob(@RequestParam(value="jobId") String jobId) {
		JobModel vo= jobInfoService.getJobInfo(jobId);
		return vo ;
	}


	/* Job 정보  추가 */
	@RequestMapping("insertJob")
	public @ResponseBody HashMap<String, Object>  inserJob(
			@RequestParam(value="jobNm") String jobNm,
			@RequestParam(value="jobDesc") String jobDesc,
			@RequestParam(value="jobData") String jobData,
			@RequestParam(value="jobTmout", defaultValue="5") String jobTmout,
			@RequestParam(value="workspaceId", defaultValue="") String workspaceId,
			@RequestParam(value="jobTyp") String jobTyp,
			@RequestParam(value="authUser") String authUser,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= jobInfoService.insertJob( jobNm, jobDesc, jobData,jobTyp,  authUser, regUser,jobTmout,workspaceId);

		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Job 정보  수정 */
	@RequestMapping("updateJobInfo")
	public @ResponseBody HashMap<String, Object>  updateJob(@RequestParam(value="jobId") String jobId,
			@RequestParam(value="jobNm") String jobNm,
			@RequestParam(value="jobDesc") String jobDesc,
			@RequestParam(value="jobData") String jobData,
			@RequestParam(value="workspaceId", defaultValue="") String workspaceId,
			@RequestParam(value="jobTmout" , defaultValue="5") String jobTmout,
			@RequestParam(value="jobTyp") String jobTyp) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= jobInfoService.updateJobInfo(jobId, jobNm, jobDesc, jobData,jobTyp,jobTmout,workspaceId);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Job 정보  삭제 */
	@RequestMapping("deleteJob")
	public @ResponseBody HashMap<String, Object>  deleteJob(@RequestParam(value="jobId") String jobId ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {		
			resunt_cnt= jobInfoService.deleteJob(jobId);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}               

    
}
