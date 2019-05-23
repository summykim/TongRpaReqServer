package com.skcc.tongrpa.chbotgw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcc.tongrpa.jobReq.JobExecReqModel;
import com.skcc.tongrpa.jobReq.JobExecReqService;

public class chatBotRltController {
	@Autowired
	private JobExecReqService jobReqService;
	
	@GetMapping({"/", "/JobExecResult"})
	public String  listmap(Model model,
			@RequestParam(value="jobExecReqId", required=true) String jobExecReqId) {

		JobExecReqModel vo= jobReqService.getJobExecReqInfo(jobExecReqId);
		model.addAttribute("result",vo);

        return "/JobExecResult";
	}
}
