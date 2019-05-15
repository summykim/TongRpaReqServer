package com.skcc.tongrpa.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class jobController {


	@Autowired
	private MqSenderService mqSenderService;
	

    @RequestMapping("/sendMqMessage")
	 public String  sendMessage2(@RequestParam(value="name", defaultValue="World") String name) {
		 String routingKey="agent.pc1.*";
		 String sendMsg="https://daum.net";

        
	     mqSenderService.sendMqMessage(routingKey,sendMsg);
		return "success";
	 }

    
    /* Job search  */
    @RequestMapping("searchJob")
	 public String  searchJobList() {

		return "success";
	}    
     
    /* Job 실행요  */
    @RequestMapping("jobExecReq")
	 public String  jobExecReq() {

		return "success";
	}   
    
}
