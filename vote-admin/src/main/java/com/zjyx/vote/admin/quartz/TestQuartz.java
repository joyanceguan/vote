package com.zjyx.vote.admin.quartz;

import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestQuartz {
	
	@Resource
	private ExecutorService typeRankExecutor;

	int x = 1;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void x(){
		for(int i =0;i<15;i++){
			typeRankExecutor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread()+"======================="+x++);
				}
			});
		}
		
	}
}
