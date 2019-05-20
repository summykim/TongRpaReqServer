package com.skcc.tongrpa.mq;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.job.JobModel;

@Service
public class MqSenderService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final String EXCHANGE_NAME = "tong-rpa-exchange";

	private static final Logger logger = LoggerFactory.getLogger(MqSenderService.class);
	
	private static final String BIND_PREFIX ="agent";


	public void sendMqMessage(String routingKey, String json) {

		logger.info(" sending the message with routing key {}", routingKey);

		rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, json);
	}
	
	public boolean jobExecRegMQ(String jobExecReqId, String agentId,JobModel jm) {
		
		// MQ 전송용 JSON 생성
		JSONObject jobj=new JSONObject();
		boolean result=true;
		try {
			jobj.put("exec_req_id", jobExecReqId);
			jobj.put("agent_id", agentId);
			jobj.put("req_data", jm.getJob_data());
			jobj.put("req_typ", jm.getJob_typ());
			
			// 대상 Agent 지정 
			String routingKey=BIND_PREFIX+"."+ agentId+".req";
			
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
	
	// 에이전트 상태  결과 테스트
	public boolean agentHealthMqReturn(String agentId, String status) {
		
		// MQ 전송용 JSON 생성
		JSONObject jobj=new JSONObject();
		boolean result=true;
		try {
			jobj.put("AGNT_ID", agentId);
			jobj.put("AGNT_STATUS", status);
			jobj.put("RESP_TYPE", "HLTH");
			
			// 대상 Agent 지정 
			String routingKey=BIND_PREFIX+".result";
			
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
	
	// 에이전트 상태  결과 테스트
	public boolean agentExecResponseMqReturn(String jobExecReqId, String agentId,String agentStaus,String execStaus) {
		
		// MQ 전송용 JSON 생성
		JSONObject jobj=new JSONObject();
		boolean result=true;
		try {
			jobj.put("EXEC_REQ_ID", jobExecReqId);
			jobj.put("AGNT_ID", agentId);
			jobj.put("AGENT_STATUS", agentStaus);
			jobj.put("EXEC_STATUS", execStaus);
			jobj.put("RESP_TYPE", "RLT");
	
			
			// 대상 Agent 지정 
			String routingKey=BIND_PREFIX+".result";
			
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
}
