package com.zjyx.vote.admin.quartz;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zjyx.vote.api.model.constants.RedisKey;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.transaction.IVoteRecordTransSerivce;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.ReturnData;

/**
 * 从redis队列中获取投票记录，批量保存到数据库中
 */
//@Component
public class VoteRecordJob {
	
	private static final Logger errorLog = LoggerFactory.getLogger("errorLog");
	
	//发送redis失败重试次数
	public final static int retryTime = 3;

	@Resource
	RedisTemplate<String, VoteRecord> redisTemplate;
	
	@Resource
	IVoteRecordTransSerivce voteRecordTransSerivce;
	
	//每10s执行一次同步数据库
//	@Scheduled(cron = "0/10 * * * * ? ")
	public void saveVoteRecord(){
		Set<VoteRecord> set = redisTemplate.opsForSet().members(RedisKey.VOTERECORD_SAVE_KEY);
		if(set.size() > 0){
			ReturnData<Integer> returnData = voteRecordTransSerivce.batchSave(set);
			if(returnData.getErrorType() == Error_Type.SUCCESS){
				removeVoteRecord(set);
			}
		}
	}
	
	public void removeVoteRecord(Set<VoteRecord> set){
		int index = 0;
    	boolean isSuccess = false;
        while(index < retryTime && !isSuccess){
        	try{
        		redisTemplate.opsForSet().remove(RedisKey.VOTERECORD_SAVE_KEY, set);
        	    isSuccess = true;
        	}catch(Exception e){
        		//日志
        		errorLog.error("save redis exception", e);
        		index++;
        	}
        }
        //如果重试后仍然没能成功
		if(!isSuccess){
			// JOY TODO
		}
	}
}
