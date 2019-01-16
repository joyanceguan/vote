package com.zjyx.vote.test.helloworld;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class Test {

	public static void main(String[] args) {
//		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//		executorService.scheduleAtFixedRate(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("helloworld");
//			}
//		}, 5, 1, TimeUnit.SECONDS);
//		
//		
//		
//		ExecutorService executorService = Executors.newFixedThreadPool(2);
//		for(int i = 0; i < 3;i++){
//			executorService.execute(new Runnable(){
//
//				@Override
//				public void run() {
//                     try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}			
//                     System.out.println(Thread.currentThread().getName()+" say hello");
//				}});
//		}
		
		
//		RejectedExecutionHandler defaultHandler =
//			        new AbortPolicy();
//		BlockingQueue<Runnable> bq = new LinkedBlockingQueue<Runnable>(10);
//		ThreadPoolExecutor pools = new ThreadPoolExecutor(5, 10, 1, TimeUnit.MILLISECONDS, bq, Executors.defaultThreadFactory(), defaultHandler);
//		for(int i = 1;i < 21;i++){
//			int j = i;
//			pools.execute(new Runnable(){
//
//				@Override
//				public void run() {
//			      try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//                   System.out.println(j+":"+Thread.currentThread().getName());					
//				}
//				
//			});
//		}
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
		for(int i =0;i<6; i++){
			executorService.scheduleWithFixedDelay(new Runnable(){
				@Override
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" hello");
				}}, 5, 5, TimeUnit.SECONDS);
		}
	}
	
}
