package com.skcc.tongrpa.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skcc.tongrpa.mq.MqSenderService;

@RestController
public class userController {


	@Autowired
	private MqSenderService mqSenderService;
	

    /* 사용자 정보 조회 */
    @RequestMapping("searchUser")
	 public String  searchUser() {

		return "success";
	}
    

    
}
