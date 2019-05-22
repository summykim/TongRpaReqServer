package com.skcc.tongrpa.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.skcc.tongrpa.mq.MqSenderService;

@Component
public class ScheduledTasks {
	
	@Autowired
	MqSenderService mqSender;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
/*
	@Scheduled(fixedRate = 10000)
	public void performTask() {

		System.out.println("Regular task performed at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(initialDelay = 1000, fixedRate = 60000)
	public void performDelayedTask() {

		System.out.println("Delayed Regular task performed at "
				+ dateFormat.format(new Date()));

	}
*/
	/* Agent Health Check Request */
	@Scheduled(cron = "0 * * * * *")
	public void performTaskUsingCron() {

		logger.info("Agent Health Check Request "+ dateFormat.format(new Date()));
		
		mqSender.agentHealthMqRequest();

	}
}
