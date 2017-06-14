package cn.jeeweb.modules.task.utils;

import cn.jeeweb.core.quartz.data.ScheduleJob;
import cn.jeeweb.modules.task.entity.ScheduleJobEntity;

public class ScheduleJobUtils {
     public static ScheduleJob entityToData(ScheduleJobEntity scheduleJobEntity){
    	 ScheduleJob scheduleJob=new ScheduleJob();
    	 scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
    	 scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
    	 scheduleJob.setDescription(scheduleJobEntity.getDescription());
    	 scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
    	 scheduleJob.setJobName(scheduleJobEntity.getJobName());
    	 scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
    	 scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
    	 scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
    	 scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
    	 return scheduleJob;
     }
}
