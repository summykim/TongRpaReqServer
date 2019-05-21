package com.skcc.tongrpa.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.job.JobService;
import com.skcc.tongrpa.jobReq.JobExecReqService;
import com.skcc.tongrpa.user.UserService;




/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class MqMessageListener {
	
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

    private static final Logger log = LogManager.getLogger(MqMessageListener.class);

    public MqMessageListener() {
       
    }

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootRabbitMQApplication} for more details
     * @param message
     */
    
    public void receiveMessage(String message) {
    	log.info("Received <" + message+ ">");
    	ObjectMapper objectMapper = new ObjectMapper();
    //	Gson gson = new Gson();
    	String mappedValue="";
		try {
			mappedValue = objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        log.info("Received OBJ<" + mappedValue + ">");

    }
    
    public void receiveMessage(Object object) {
    	byte[] bytes=null;
    	if (object instanceof byte[]) {
    		bytes = (byte[]) object;

    	}
    	else if (object instanceof String) {
    		try {
    			bytes = ((String) object).getBytes("utf-8");
    		}
    		catch (UnsupportedEncodingException e) {
    			throw new MessageConversionException(
    					"failed to convert to Message content", e);
    		}

    	}
    	else if (object instanceof Serializable) {
    		try {
    			bytes = SerializationUtils.serialize(object);
    		} catch (IllegalArgumentException e) {
    			throw new MessageConversionException(
    					"failed to convert to serialized Message content", e);
    		}

    	}

    

    	 String message = new String(bytes);
    	
    	log.info("Received <" + message+ ">");
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    	MqRecvModel mqRecvModel = null;
		try {
			mqRecvModel = objectMapper.readValue(message, MqRecvModel.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		log.debug("mqRecvModel :",mqRecvModel);
		if(mqRecvModel!=null) {//수신값 존재합

			    String resType=mqRecvModel.getRes_typ();
			    String agentId=mqRecvModel.getAgent_id();
				log.info("getReq_type <" + resType + ">");
				log.info("getAgent_id <" + mqRecvModel.getAgent_id() + ">");
				String agentStatus=mqRecvModel.getAgent_status(); // 수신된 Agent 상태값
				// Agent 상태체크 
				if(resType.equals("HLTH")) {
					agService.updateAgentStatus(agentId, agentStatus); // Agent 상태값으로 DB  업데이트
				}
				// Job실행 결과 처리
				else if(resType.equals("RLT")) {
					String jobExecReqId=mqRecvModel.getExec_req_id();
					String jobStatus=mqRecvModel.getJob_status();
					
					if(jobStatus.equals("ING")) {// 실행 진행중
						jobReqService.updateJobExecReqStatus(jobExecReqId,jobStatus);
						
					}else if(jobStatus.equals("CMP")) {//실행 완료
						jobReqService.updateJobExecReqInfo(jobExecReqId,jobStatus,mqRecvModel.getRlt_data());
						
					}
					
					agService.updateAgentStatus(agentId, agentStatus); // Agent 상태값으로 DB  업데이트

				}
		}

    }


}
