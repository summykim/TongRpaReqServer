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
public class jobController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private jobService jobInfoService;
    
    /* agent 정보조   */
	@RequestMapping("/jobList")
	public @ResponseBody List<jobModel> getJobList() {

		List<jobModel> list=  jobInfoService.getJobList();

		return   list;

	}

	/* Job 정보 조회 */
	@RequestMapping("searchJob")
	public @ResponseBody jobModel  searchJob(@RequestParam(value="jobId") String jobId) {
		jobModel vo= jobInfoService.getJobInfo(jobId);
		return vo ;
	}


	/* Job 정보  추가 */
	@RequestMapping("insertJob")
	public @ResponseBody HashMap<String, Object>  inserJob(@RequestParam(value="jobId") String jobId,
			@RequestParam(value="jobNm") String jobNm,
			@RequestParam(value="jobDesc") String jobDesc,
			@RequestParam(value="jobData") String jobData,
			@RequestParam(value="jobSeq") String jobSeq,
			@RequestParam(value="authUser") String authUser,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= jobInfoService.insertJob(jobId, jobNm, jobDesc, jobData, jobSeq, authUser, regUser);

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
			@RequestParam(value="jobData") String jobData) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= jobInfoService.updateJobInfo(jobId, jobNm, jobDesc, jobData);
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
