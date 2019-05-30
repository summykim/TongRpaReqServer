package com.skcc.tongrpa.chbotgw;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.jobReq.JobExecReqController;
import com.skcc.tongrpa.jobReq.JobExecReqModel;
import com.skcc.tongrpa.jobReq.JobExecReqService;
@Controller
public class chatBotRltController {
	private static final Logger logger = LoggerFactory.getLogger(chatBotRltController.class);
	
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
	public String  CheckAgentStatus(Model model			,
			@RequestParam(value="staDt" , defaultValue="") String staDt,
			@RequestParam(value="endDt" , defaultValue="") String endDt) {


		List<agentModel> list = agentService.getAgentList("");
		
		model.addAttribute("result",list);
		
		
		String staDtm="";
		String endDtm="";
		if(staDt.length()==10 &&  endDt.length()==10) {
			staDtm=staDt+" 00:00:00";
			endDtm=endDt+" 23:59:59";
		}
		
		
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		if(staDt.length()==0) {
			staDtm=today+" 00:00:00";
		}
		if(endDt.length()==0) {
			endDtm=today+" 23:59:59";
		}
		
		logger.info(staDtm + " ~ " +endDtm);
		List<HashMap> statlist = jobReqService.getJobExecReqStat(staDtm, endDtm);
		model.addAttribute("resultStat",statlist);

        return "/CheckAgentStatus";
	}
}
