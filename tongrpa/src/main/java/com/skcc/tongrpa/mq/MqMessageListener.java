package com.skcc.tongrpa.mq;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class MqMessageListener {


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
    
    public void receiveMessage(byte[] bMessage) {
        String message="";
		try {
			message = new String(bMessage, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	log.info("Received byte <" + message+ ">");



    }
}
