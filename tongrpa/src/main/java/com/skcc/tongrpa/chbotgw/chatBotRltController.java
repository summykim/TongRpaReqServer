package com.skcc.tongrpa.chbotgw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.jobReq.JobExecReqModel;
import com.skcc.tongrpa.jobReq.JobExecReqService;
@Controller
public class chatBotRltController {
	@Autowired
	private JobExecReqService jobReqService;
	
	@Autowired
	private agentService agentService;
	
	@GetMapping({"/JobExecResult" })
	public String  listmap(Model model,
			@RequestParam(value="jobExecReqId", required=true) String jobExecReqId) {


		JobExecReqModel vo= jobReqService.getJobExecReqInfo(jobExecReqId);
		
		 // 값코드를 이름으로 변경 
		String jobStaus=changeCode2Nm(vo.getJob_status());
		//vo.setJob_status(jobStaus);
		model.addAttribute("result",vo);

        return "/JobExecResult";
	}
	
	private String changeCode2Nm(String code) {
		String codeNm="";
		if(code.equals("CMP"))codeNm="실행완료";
		else if(code.equals("ING"))codeNm="실행중";
		else if(code.equals("REQ"))codeNm="요청";
		return codeNm;
	}
	@GetMapping({"/CheckAgentStatus" })
	public String  CheckAgentStatus(Model model) {


		List<agentModel> list = agentService.getAgentList("");

		
		model.addAttribute("result",list);

        return "/CheckAgentStatus";
	}
}
