package com.skcc.tongrpa.agent;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class agentController {

	@Autowired
	private MqSenderService mqSenderService;
    
    /* agent 정보조   */
    @RequestMapping("getAgentInfo")
	 public String  getAgentInfo() {
		return "success";
	}    

    
}
