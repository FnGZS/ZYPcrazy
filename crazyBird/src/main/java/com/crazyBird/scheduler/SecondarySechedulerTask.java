package com.crazyBird.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crazyBird.service.live.LiveService;
import com.crazyBird.utils.RestLogUtils;

@Component
public class SecondarySechedulerTask {
	private static final Logger logger = LoggerFactory.getLogger(SecondarySechedulerTask.class);
	@Autowired private LiveService liveService;
   /* @Scheduled(cron = "0/2 * * * * ?")//每隔2秒隔行一次
    public void test2()
    {
    	RestLogUtils.writeSchedulerTaskByInfo("二手开始执行", logger);
        System.out.println("二手开始执行");
    }*/
}
