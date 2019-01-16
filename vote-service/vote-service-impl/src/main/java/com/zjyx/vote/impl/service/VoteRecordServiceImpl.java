package com.zjyx.vote.impl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zjyx.vote.api.model.condition.VoteRankListCdt;
import com.zjyx.vote.api.model.constants.RedisKey;
import com.zjyx.vote.api.model.dto.ResponseTimeDto;
import com.zjyx.vote.api.model.dto.VoteRuleDto;
import com.zjyx.vote.api.model.enums.Sex;
import com.zjyx.vote.api.model.persistence.User;
import com.zjyx.vote.api.model.persistence.Vote;
import com.zjyx.vote.api.model.persistence.VoteRecord;
import com.zjyx.vote.api.model.result.VoteRecordResult;
import com.zjyx.vote.api.model.result.VoteResult;
import com.zjyx.vote.api.service.IVoteRecordService;
import com.zjyx.vote.api.utils.VoteRecordUtils;
import com.zjyx.vote.common.constants.ErrorCode;
import com.zjyx.vote.common.constants.VoteConstants;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.PageInfo;
import com.zjyx.vote.common.model.ReturnData;
import com.zjyx.vote.common.utils.UUIDUtils;
import com.zjyx.vote.impl.mapper.VoteMapper;
import com.zjyx.vote.impl.mapper.VoteRecordMapper;

@Service
public class VoteRecordServiceImpl implements IVoteRecordService{
   
	private static final Logger errorLog = LoggerFactory.getLogger("errorLog");
	
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Resource
	VoteMapper voteMapper;
	
	@Resource
	VoteRecordMapper voteRecordMapper;
	
	
	private ReturnData<Integer> isCanVote(User user,Long vote_id){
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		Vote vote = voteMapper.selectById(vote_id);
		String limitRule = vote.getLimit_rule();
		VoteRuleDto voteRuleDto = JSON.parseObject(limitRule, VoteRuleDto.class);
		//登录限制
		if(voteRuleDto.isLoginLimit()){
			if(user == null){
				returnData.setErrorInfo(Error_Type.SERVICE_ERROR, ErrorCode.RULE_LIMIT, "登录限制");
			    return returnData;
			}
			String table_name = VoteRecordUtils.getRecordTableName(vote);
			List<VoteRecordResult> recordList = voteRecordMapper.getByVoteIdUserId(vote_id,user.getId(),table_name);
			//投票次数限制
			if(voteRuleDto.isEveryoneTotalLimit()){
				int everyoneCount = voteRuleDto.getEveryoneCount();
				int totalVoteCount = recordList == null ? 0 : recordList.size();
				if(everyoneCount <= totalVoteCount){
					//已经最高次数
					returnData.setErrorInfo(Error_Type.SERVICE_ERROR, ErrorCode.RULE_LIMIT, "已达投票次数上限");
				    return returnData;
				}
			}
			//投票频率限制(算法有点晦涩难懂)
			if(voteRuleDto.isEveryoneRateLimit()){
				int everyoneRateCount = voteRuleDto.getEveryoneRateCount();
				int everyoneTime = voteRuleDto.getEveryoneTime();
				if(recordList!=null && recordList.size() > 0){
					Date now = new Date();
					Calendar c = Calendar.getInstance();
					VoteRecordResult v = recordList.get(0);
					c.setTime(v.getCreate_time());
					//获取时间最早的这条投票记录
					do {
						v = getFirst(recordList,c.getTime());
						if(v != null){
							c.setTime(v.getCreate_time());
							c.add(Calendar.MINUTE, everyoneTime);
						}else{
							break;
						}
					} while(c.getTime().compareTo(now) < 0);
					
					if(v!=null){
						int count = getSize(recordList,v.getCreate_time());
						if(count >= everyoneRateCount){
							returnData.setErrorInfo(Error_Type.SERVICE_ERROR, ErrorCode.RULE_LIMIT, "已达投票频率上限");
							return returnData;
						}
					}
			    }
		    }
		}else{
			//非登录限制
	    }
		return returnData;
	}
	
	private VoteRecordResult getFirst(List<VoteRecordResult> recordList,Date time){
		VoteRecordResult voteRecord = null;
		for(int i=0;i<recordList.size();i++){
			VoteRecordResult record = recordList.get(i);
		    if(record.getCreate_time().compareTo(time) >= 0){
		    	voteRecord = record;
		    	break;
		    }
		}
		return voteRecord;
	}
	
	private int getSize(List<VoteRecordResult> recordList,Date time){
		for(int i=0;i<recordList.size();i++){
			VoteRecordResult record = recordList.get(i);
		    if(record.getCreate_time().compareTo(time) >= 0){
		    	return recordList.size() - i;
		    }
		}
		return 0;
	}
	
	private ReturnData<Integer> verfiySave(List<VoteRecord> voteRecords){
		ReturnData<Integer> returnData = new ReturnData<Integer>();
		if(voteRecords == null || voteRecords.isEmpty()){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
        for(VoteRecord voteRecord:voteRecords){		
        	if(voteRecord == null){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(StringUtils.isBlank(voteRecord.getOption_desc())){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(voteRecord.getResponse_time() == null){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(voteRecord.getCreate_time() == null){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(voteRecord.getOption_id() == null || voteRecord.getOption_id() < 1){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(voteRecord.getSee_type() == null){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
    		if(voteRecord.getVote_id() == null){
    			returnData.setErrorType(Error_Type.PARAM_ERROR);
    			return returnData;
    		}
        }
		return returnData;
	}
	
	/*
	 * 发送投票记录给redis
	 */
    private Long recordToRedis(VoteRecord voteRecord){
    	Long flag = null;
    	try{
    		flag =redisTemplate.opsForSet().add(RedisKey.VOTERECORD_SAVE_KEY, voteRecord);
    	}catch(Exception e){
    		//日志
    		errorLog.error("save redis exception", e);
    	}
    	return flag;
    }
    
    private void resendRecordToRedis(VoteRecord voteRecord,Long flag){
    	//如果失败了 重试
        if(flag == null || flag < 1 ){
        	int retryTime = 0;
        	while(retryTime < VoteConstants.RESEND_REDIS_TIME && (flag == null || flag < 1)){
        		flag = recordToRedis(voteRecord);
        		retryTime++;
            }
        	//如果重试几次还不成功,可以异步线程扔进数据库
        	if(flag == null || flag < 1){
        		// JOY TODO
        	}
        }
    }
    
    private void sendRecordToRedis(VoteRecord voteRecord){
    	Long flag = recordToRedis(voteRecord);
    	resendRecordToRedis(voteRecord,flag);
    }
    
    
    /*
	 * 发送投票记录给redis
	 */
    private Double countToRedis(Long voteId){
    	Double flag = null;
    	try{
    		//更新统计redis
    		redisTemplate.opsForZSet().incrementScore(RedisKey.VOTE_RANK_KEY, voteId, 1);
    	}catch(Exception e){
    		//日志
    		errorLog.error("save redis exception", e);
    	}
    	return flag;
    }
    
    private void resendCountToRedis(Long voteId,Double flag){
    	//如果失败了 重试
        if(flag == null || flag < 1 ){
        	int retryTime = 0;
        	while(retryTime < VoteConstants.RESEND_REDIS_TIME && (flag == null || flag < 1)){
        		flag = countToRedis(voteId);
        		retryTime++;
            }
        	//如果重试几次还不成功,可以异步线程扔进数据库
        	if(flag == null || flag < 1){
        		// JOY TODO
        	}
        }
    }
    
    private void sendCountToRedis(Long voteId){
    	Double flag = countToRedis(voteId);
    	resendCountToRedis(voteId,flag);
    }

	@Override
	public PageInfo<VoteResult> rankList(VoteRankListCdt condition) {
		PageInfo<VoteResult> pageinfo = new PageInfo<VoteResult>();
		if(condition == null || !condition.isRightPageInfo()){
			pageinfo.setErrorType(Error_Type.PARAM_ERROR);
			return pageinfo;
		}
		int count = 0;
		if(condition.isNeedTotalResults()){
		    count = voteRecordMapper.rankListCount(condition);
		}
		List<VoteResult> list = voteRecordMapper.rankList(condition);
		pageinfo.setPageInfo(condition ,Long.valueOf(count), list, null);
		return pageinfo;
	}

	@Override
	public ReturnData<Integer> batchSave(List<VoteRecord> records, User user,Long voteId) {
		ReturnData<Integer> returnData = verfiySave(records);
		//参数校验
		if(returnData.getErrorType()!=Error_Type.SUCCESS){
			return returnData;
		}
		//投票规则校验
		returnData = isCanVote(user,voteId);
		if(returnData.getErrorType()!=Error_Type.SUCCESS){
			return returnData;
		}
		//赋值
		String batchNum = UUIDUtils.getUUId();
		int age = -1;
		Sex sex = Sex.unknown;
		Long userId = null;
		if(user!=null){
			age = user.getAge();
			sex = user.getSex();
			userId = user.getId();
		}
		for(VoteRecord voteRecord : records){
			voteRecord.setVote_id(voteId);
			voteRecord.setId(UUIDUtils.getUUId());
			voteRecord.setBatch_num(batchNum);
			voteRecord.setAge(age);
			voteRecord.setSex(sex);
			voteRecord.setUser_id(userId);
		}
		//发送redis 之后优化时做
//		sendRecordToRedis(voteRecord); 
		int flag = voteRecordMapper.batchSave(records);
		//更新统计redis
		sendCountToRedis(voteId);
		returnData.setResultData(flag);
		return returnData;
	}

	@Override
	public ReturnData<List<ResponseTimeDto>> responseTimeCount(Long voteId) {
		ReturnData<List<ResponseTimeDto>> returnData = new ReturnData<List<ResponseTimeDto>>();
		if(voteId == null || voteId < 1){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		Vote vote = voteMapper.selectById(voteId);
		if(vote == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		String tableName = VoteRecordUtils.getRecordTableName(vote);
		List<Integer> responseTimeCount = voteRecordMapper.responseTimeCount(voteId, tableName);
		if(responseTimeCount != null && !responseTimeCount.isEmpty()){
			List<ResponseTimeDto> resultList = new ArrayList<ResponseTimeDto>(5);
			int totalCount = 0;
			for(int i=0;i<responseTimeCount.size();i++){
				int count = responseTimeCount.get(i);
				totalCount += count;
				ResponseTimeDto dto = new ResponseTimeDto();
				if(i==0){
					dto.setTimeDesc("<=1s");
				}else if(i==1){
					dto.setTimeDesc("1-3s");
				}else if(i==2){
					dto.setTimeDesc("3-5s");
				}else if(i==3){
					dto.setTimeDesc("5-10s");
				}else if(i==4){
					dto.setTimeDesc(">5s");
				}
				dto.setCount(count);
				resultList.add(dto);
			}
			if(totalCount!=0){
				for(ResponseTimeDto dto : resultList){
					dto.setPercent("%" + String.format("%.2f", dto.getCount()/totalCount));
				}
				returnData.setResultData(resultList);
			}
		}
		return returnData;
	}

	@Override
	public ReturnData<List<VoteResult>> chooseOptionsCount(Long voteId) {
		ReturnData<List<VoteResult>> returnData = new ReturnData<List<VoteResult>>();
		if(voteId == null || voteId < 1){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		Vote vote = voteMapper.selectById(voteId);
		if(vote == null){
			returnData.setErrorType(Error_Type.PARAM_ERROR);
			return returnData;
		}
		String tableName = VoteRecordUtils.getRecordTableName(vote);
		List<VoteResult> list = voteRecordMapper.chooseOptionsCount(voteId, tableName);
		returnData.setResultData(list);
		return returnData;
	}
	
}


