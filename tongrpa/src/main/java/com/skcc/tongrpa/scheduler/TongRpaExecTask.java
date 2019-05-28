package com.skcc.tongrpa.scheduler;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.agent.agentModel;
import com.skcc.tongrpa.agent.agentService;
import com.skcc.tongrpa.job.JobModel;
import com.skcc.tongrpa.job.JobService;
import com.skcc.tongrpa.jobReq.JobExecReqService;
import com.skcc.tongrpa.mq.MqSenderService;
import com.skcc.tongrpa.utils.HttpUtil;

@EnableAsync
public class TongRpaExecTask implements Runnable {
	

	
    private String Id;
    private String JobId;
    private final String HostNmae="http://localhost:18080";
    public TongRpaExecTask(String Id,String  JobId) {
      this.Id = Id;
      this.JobId = JobId;
    }
    
    //작업 시작 
    @Override
    public void run() {
      
    	 System.out.println("TongRpaExecTask : " + Id +" / JobId : " + JobId );
    	 String targetURL=HostNmae+"/BatchjobExecReq";
    	 String urlParameters="jobId="+JobId +"&batchId="+Id;
    	 String result=HttpUtil.executePost(targetURL, urlParameters);
    	 
    	 System.out.println("TongRpaExecTask : " + Id +" / JobId : " + JobId  +" / result : " +result );
    }

}
