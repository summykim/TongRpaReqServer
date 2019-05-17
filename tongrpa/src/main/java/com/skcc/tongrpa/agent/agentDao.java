package com.skcc.tongrpa.agent;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 *  Agent 정보 관리 DAO
 */
@Mapper
public interface agentDao {
	public List<agentModel> getAgentList();
	public agentModel getAgentInfo(String agentId);
	public int insertAgent(String agentId,String agentNm,String agentDesc,String agentUid,String agentStatus,String regUser);
	public int updateAgentInfo(String agentId,String agentNm,String agentDesc,String agentUid,String agentStatus);
	public int updateAgentUid(String agentId,String agentUid);
	public int updateAgentStatus(String agentId,String agentStatus);	
	public int deleteAgent(String agentId);
	public List<agentModel> getIdleAgentList();

}

