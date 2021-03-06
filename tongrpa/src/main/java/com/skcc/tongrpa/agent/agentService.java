package com.skcc.tongrpa.agent;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class agentService {

	@Autowired
	private agentDao agentdao;


	private static final Logger logger = LoggerFactory.getLogger(agentService.class);

	/*
	 * Agent 정보 조회
	 */
	public agentModel getAgentInfo(String agentId ,String agentUid){



		return agentdao.getAgentInfo(agentId,agentUid );

	}
	
	/*
	 * Idle 에이전트 조회
	 */
	public List<agentModel> getIdleAgentList(){



		return agentdao.getIdleAgentList();

	}

	
	/*
	 * Agent 전체  목록  조회
	 */
	public List<agentModel> getAgentList (String  searchText) {

		HashMap<String, String> hm =new HashMap<String, String>();
		hm.put("searchText", searchText);

		return agentdao.getAgentList(hm);

	}
	/*
	 * Agent 추가 
	 */
	public int  insertAgent(String agentId,String agentNm,String agentDesc,String agentUid,String agentStatus,String regUser) {
		return agentdao.insertAgent( agentId, agentNm, agentDesc, agentUid, agentStatus,regUser);
	}
	/*
	 * Agent 정보  수정
	 */
	public int  updateAgentInfo(String agentId,String agentNm,String agentDesc,String agentUid,String agentStatus) {
		return agentdao.updateAgentInfo( agentId, agentNm, agentDesc, agentUid, agentStatus);
	}

	/*
	 * Agent UID 정보  수정
	 */
	public int  updateAgentUid(String agentId,String agentUid) {
		return agentdao.updateAgentUid( agentId,  agentUid);
	}	
	
	/*
	 * Agent 상태 정보  수정
	 */
	public int  updateAgentStatus(String agentId,String agentStatus) {
		return agentdao.updateAgentStatus( agentId,  agentStatus);
	}		
	/*
	 * Agent 정보  삭제
	 */
	public int  deleteAgent (String agentId) {

		return agentdao.deleteAgent( agentId);
	}

}
