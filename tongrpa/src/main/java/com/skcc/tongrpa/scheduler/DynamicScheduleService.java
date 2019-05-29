package com.skcc.tongrpa.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class DynamicScheduleService {

	
    // Task Scheduler
    TaskScheduler scheduler;
    
    // A map for keeping scheduled tasks
    Map<String , ScheduledFuture<?>> jobsMap = new HashMap<>();
    
    public DynamicScheduleService(TaskScheduler scheduler) {
    	
    
        this.scheduler = scheduler;

    }
    
    
    
    // Schedule Task to be executed every night at 00 or 12 am
    public boolean addTaskToScheduler(String  taskId,String jobId, String cron) {
    	 boolean result=false;
    	try {
    		
    		//존재하면 삭제
    		if(getTaskFromScheduler(taskId)) {
    			removeTaskFromScheduler(taskId);
    		}
    		CronTrigger crnTrigger = new CronTrigger(cron);

    		Runnable  TongRpaExecTask  = new  TongRpaExecTask(taskId ,jobId);

    		ScheduledFuture<?> scheduledTask = scheduler.schedule(TongRpaExecTask, crnTrigger);

    		jobsMap.put(taskId, scheduledTask);
    		result=true;
    	}catch (Exception ex) {
    		ex.printStackTrace();

    	}
    	return result;
    }
    // scheduled task status 
    public boolean getTaskFromScheduler(String  taskId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(taskId);
        boolean result=false;
        if(scheduledTask != null) {
        	result=true;
        }
        return result;
    }
        
    // Remove scheduled task 
    public boolean removeTaskFromScheduler(String  taskId) {
    	boolean result=false;
        ScheduledFuture<?> scheduledTask = jobsMap.get(taskId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(taskId, null);
            result=true;
        }
        
        return result;
    }
    
    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }
}
