package com.zjyx.vote.admin.quartz;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zjyx.vote.api.model.condition.VoteRankListCdt;
import com.zjyx.vote.api.model.constants.RedisKey;
import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.api.service.IVoteRecordService;
import com.zjyx.vote.api.utils.VoteRecordUtils;
import com.zjyx.vote.common.model.PageInfo;

/**
 * 首页下面根据用户行为分类统计
 */
@Component
public class UserActionJob {
	
	private static final Logger errorLog = LoggerFactory.getLogger("errorLog");
	
	@Resource
	IVoteRecordService voteRecordService;
	
	@Resource
	private ExecutorService typeRankExecutor;
	
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	//发送redis失败重试次数
	public final static int retryTime = 3;
		

	@Scheduled(cron = "0 0/1 * * * ?")
	public void userAction(){
		int currentPage = 1;
		int onePageSize = 100000;
		VoteRankListCdt cdt = new VoteRankListCdt();
		cdt.setCurrentPage(currentPage);
		cdt.setOnePageSize(onePageSize);
		Sex[] sexArray = new Sex[]{Sex.male,Sex.female}; 
		String[] allTables = VoteRecordUtils.getAllTables();
		for(Sex sex:sexArray){
			CountDownLatch latch = new CountDownLatch(allTables.length);
			List<VoteResult> sortList = new LinkedList<VoteResult>();
			//多线程读取分表数据
			for(String tableName:allTables){
				typeRankExecutor.execute(new Runnable(){
					@Override
					public void run() {
						cdt.setSex(sex);
						cdt.setTable_name(tableName);
						PageInfo<VoteResult> pageInfo = voteRecordService.rankList(cdt);
						List<VoteResult> list = pageInfo.getObjects();
						if(list!=null && !list.isEmpty()){
						   synchronized (this) {
							   sortList.addAll(list);
						   }
						}
						latch.countDown();
					}
				});
			}
			try {
				latch.await();
				//排序
				sort(sortList);
				//将统计信息发送redis
				sendToRedis(sortList,sex);
			} catch (Exception e) {
	    		errorLog.error("save redis exception", e);
			}
		}
	}
	
	//按访问量倒序排
	private static void sort(List<VoteResult> customers){
	      if(customers == null || customers.size()==0)
	    	  return;
	      Collections.sort(customers, new Comparator<VoteResult>(){
	    
	    	@Override
	    	public int compare(VoteResult o1, VoteResult o2) {
	    		if(o1.getCount() > o2.getCount()){
	    			return -1;
	    		}else
	    			return 1;
	    	}});
	      
	 }
	
	/**
     * 发送redis并带有重试机制(重试几次失败也没关系，仍然使用以前的排行)
     * @param rankedList
     * @param typeId
     */
    private void sendToRedis(List<VoteResult> rankedList,Sex sex){
    	if(rankedList == null || rankedList.isEmpty())
    		return;
    	//排序后存放redis
		String redisKey = RedisKey.SEX_PREFIX+sex.getId();
    	int index = 0;
    	boolean isSuccess = false;
        while(index < retryTime && !isSuccess){
        	try{
        		redisTemplate.opsForValue().set(redisKey, rankedList);
        	    isSuccess = true;
        	}catch(Exception e){
        		//日志
        		errorLog.error("save redis exception", e);
        		index++;
        	}
        }
    }
}
