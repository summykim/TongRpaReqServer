package com.skcc.tongrpa.mq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.code.AgentStaus;
import com.skcc.tongrpa.job.JobModel;

@Service
public class MqSenderService {
	

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired 
	private agentService agntService;

	private static final String EXCHANGE_NAME = "tong-rpa-exchange";

	private static final Logger logger = LoggerFactory.getLogger(MqSenderService.class);
	
	private static final String BIND_PREFIX ="agent";


	public void sendMqMessage(String routingKey, String json) {

		logger.debug(" sending the message with routing key {}", routingKey);

		rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, json);
	}
	
	public boolean jobExecRegMQ(String jobExecReqId, String agentId,JobModel jm ,String req_param) {
		
		// MQ 전송용 JSON 생성
		JSONObject jobj=new JSONObject();
		boolean result=true;
		try {
			jobj.put("exec_req_id", jobExecReqId);
			jobj.put("agent_id", agentId);
			jobj.put("req_data", jm.getJob_data());
			jobj.put("req_param", req_param);
			jobj.put("req_typ", jm.getJob_typ());
			jobj.put("job_tmout", jm.getJob_tmout());
			
			// 대상 Agent 지정 
			String routingKey=BIND_PREFIX+"."+ agentId+".req";
			logger.debug("send mq < "+jobj.toString() +" >");
			sendMqMessage(routingKey,jobj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		
		return result;
	}
	
	// 에이전트 상태  결과 요청
	public void agentHealthMqRequest() {
		
		// Agent 전체 목록조회
		List<agentModel> agentList=agntService.getAgentList("");
		
		for(int i=0;i< agentList.size();i++) {
			
			agentModel agntM=agentList.get(i);
			
			
			
			// MQ 전송용 JSON 생성
			JSONObject jobj=new JSONObject();
			try {
				jobj.put("agent_id",agntM.getAgent_id() );
				jobj.put("req_typ", "HLTH");
				
				// 대상 Agent 지정 
				String routingKey=BIND_PREFIX+"."+ agntM.getAgent_id()+".req";
				sendMqMessage(routingKey,jobj.toString());
				
				// Agent DB 상태  DOWN 처리
				agntService.updateAgentStatus(agntM.getAgent_id(), AgentStaus.DOWN.toString());
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
