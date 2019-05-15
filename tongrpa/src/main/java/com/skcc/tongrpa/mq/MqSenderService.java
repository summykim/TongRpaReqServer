package com.skcc.tongrpa.mq;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSenderService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final String EXCHANGE_NAME = "tong-rpa-exchange";

	private static final Logger logger = LoggerFactory.getLogger(MqSenderService.class);



	public void sendMqMessage(String routingKey, String json) {

		logger.info(" sending the message with routing key {}", routingKey);

		rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, json);

	}
}
