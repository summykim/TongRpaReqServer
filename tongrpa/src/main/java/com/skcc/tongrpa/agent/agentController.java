package com.skcc.tongrpa.agent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;
import com.skcc.tongrpa.user.UserService;
import com.skcc.tongrpa.agent.agentModel;

@RestController
public class agentController {

	@Autowired
	private MqSenderService mqSenderService;
	
	@Autowired
	private agentService agentService;
    
    /* agent 정보조   */
	@RequestMapping("/agentList")
	public @ResponseBody List<agentModel> getAgentList(@RequestParam(value="searchText") String searchText) {

		List<agentModel> list=  agentService.getAgentList(searchText);

		return   list;

	}

	/* Agent 정보 조회 */
	@RequestMapping("searchAgent")
	public @ResponseBody agentModel  searchAgent(
			@RequestParam(value="agentId") String agentId,
			@RequestParam(value="agentUid") String agentUid
			) {
		agentModel vo= agentService.getAgentInfo(agentId,agentUid);
		return vo ;
	}


	/* Agent 정보  추가 */
	@RequestMapping("insertAgent")
	public @ResponseBody HashMap<String, Object>  inserAgent(@RequestParam(value="agentId") String agentId,
			@RequestParam(value="agentNm") String agentNm,
			@RequestParam(value="agentDesc") String agentDesc,
			@RequestParam(value="agentUid") String agentUid,
			@RequestParam(value="agentStatus") String agentStatus,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= agentService.insertAgent(agentId, agentNm, agentDesc, agentUid, agentStatus,regUser);

		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Agent 정보  수정 */
	@RequestMapping("updateAgentInfo")
	public @ResponseBody HashMap<String, Object>  updateAgent(@RequestParam(value="agentId") String agentId,
			@RequestParam(value="agentNm") String agentNm,
			@RequestParam(value="agentDesc") String agentDesc,
			@RequestParam(value="agentUid") String agentUid,
			@RequestParam(value="agentStatus") String agentStatus) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= agentService.updateAgentInfo(agentId, agentNm, agentDesc, agentUid, agentStatus);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}

	/* Agent 정보  수정 */
	@RequestMapping("updateAgentUid")
	public @ResponseBody HashMap<String, Object>  updateAgentUid(@RequestParam(value="agentId") String agentId,
			@RequestParam(value="agentUid") String agentUid) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= agentService.updateAgentUid(agentId, agentUid);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	
	/* Agent 정보  수정 */
	@RequestMapping("updateAgentStatus")
	public @ResponseBody HashMap<String, Object>  updateAgentStatus(@RequestParam(value="agentId") String agentId,
			@RequestParam(value="agentStatus") String agentStatus) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= agentService.updateAgentStatus(agentId, agentStatus);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}	
	
	/* Agent 정보  삭제 */
	@RequestMapping("deleteAgent")
	public @ResponseBody HashMap<String, Object>  deleteAgent(@RequestParam(value="agentId") String agentId ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {		
			resunt_cnt= agentService.deleteAgent(agentId);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}               

    
}
