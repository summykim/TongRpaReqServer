package com.skcc.tongrpa.scheduler;

public class TongRpaExecTask implements Runnable {
	
    private String Id;
    private String JobId;
    
    public TongRpaExecTask(String Id,String  JobId) {
      this.Id = Id;
      this.JobId = JobId;
    }
    
    //작업 시작 
    public void run() {
      System.out.println("TongRpaExecTask : " + Id +" / JobId : " + JobId );
    }
}
